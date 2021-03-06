package com.events.processor.persister;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TypeCodec;

import java.net.InetSocketAddress;
import java.util.Arrays;

public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public Session connect(String node, Integer port) {
        cluster = Cluster.builder()
                .addContactPointsWithPorts(Arrays.asList(new InetSocketAddress(node, port)))
                .build();
        cluster.getConfiguration().getCodecRegistry().register(TypeCodec.counter());
        session = cluster.connect();
        return session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}

