package com.events.processor.persister;

import com.datastax.driver.core.Session;
import com.events.processor.persister.domain.Experiment;
import com.events.processor.persister.domain.Variant;
import com.events.processor.persister.query.CassandraQuery;
import com.events.processor.persister.query.Query;

import java.util.Arrays;

public class CassandraPersister {

    public static void main(String[] args) {
        CassandraPersister cassandraPersister = new CassandraPersister();
        CassandraConnector cassandraConnector = new CassandraConnector();
        Session session = cassandraConnector.connect("localhost", 9042);
        cassandraPersister.createKeyspace("tracking", "SimpleStrategy", 1, session);
        cassandraPersister.createColumnFamily("tracking", "visitors_count_by_experiment", session);
        Query query = new CassandraQuery(session);
        Experiment experiment = new Experiment(19192, "Test Experiment", Arrays.asList(new Variant(19192, 1, 3)));
        query.insertExperimentVisitors(Arrays.asList(experiment));
        Experiment e = query.getExperimentVisitors(19192);
        System.out.println(e);
        cassandraConnector.close();

    }

    private void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor, Session session) {
        StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                .append(keyspaceName).append(" WITH replication = {")
                .append("'class':'").append(replicationStrategy)
                .append("','replication_factor':").append(replicationFactor)
                .append("};");

        String query = sb.toString();
        session.execute(query);
    }

    private void createColumnFamily(String keySpaceName, String name, Session session) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(keySpaceName).append(".")
                .append(name).append(" (")
                .append("experiment_id int, ")
                .append("variant_number int,")
                .append("visitors_count counter, PRIMARY KEY ((experiment_id),variant_number));");

        String query = sb.toString();
        session.execute(query);
    }
}
