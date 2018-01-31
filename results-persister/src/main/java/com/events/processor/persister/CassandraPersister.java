package com.events.processor.persister;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

import java.util.List;
import java.util.stream.Collectors;

public class CassandraPersister {

    public static void main(String[] args) {
        CassandraPersister cassandraPersister = new CassandraPersister();
        CassandraConnector cassandraConnector = new CassandraConnector();
        Session session = cassandraConnector.connect("localhost", 9042);
        cassandraPersister.createKeyspace("test", "SimpleStrategy", 1, session);
        ResultSet result =
                session.execute("SELECT * FROM system_schema.keyspaces;");

        List<String> matchedKeyspaces = result.all()
                .stream()
                .filter(r -> r.getString(0).equals("test"))
                .map(r -> r.getString(0))
                .collect(Collectors.toList());
        System.out.println(matchedKeyspaces);
        cassandraConnector.close();

    }

    private void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor, Session session) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }
}
