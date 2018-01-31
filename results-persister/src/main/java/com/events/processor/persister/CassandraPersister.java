package com.events.processor.persister;

import com.datastax.driver.core.Session;

public class CassandraPersister {

    public static void main(String[] args) {
        CassandraPersister cassandraPersister = new CassandraPersister();
        CassandraConnector cassandraConnector = new CassandraConnector();
        Session session = cassandraConnector.connect("localhost", 9042);
        cassandraPersister.createKeyspace("tracking", "SimpleStrategy", 1, session);
        cassandraPersister.createColumnFamily("tracking","visitors_count_by_experiment", session);
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

    private void createColumnFamily(String keySpaceName,String name, Session session) {
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
