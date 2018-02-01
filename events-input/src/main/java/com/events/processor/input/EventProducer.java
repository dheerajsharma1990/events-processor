package com.events.processor.input;

import com.events.processor.domain.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.Math.random;
import static java.util.UUID.randomUUID;

public class EventProducer {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.events.processor.serializer.EventSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, Event> eventProducer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < (random() * 10); j++) {
                map.put(randomUUID().toString(), (int) (random() * 10) % 3);
            }
            Event event = new Event(randomUUID().toString(), randomUUID().toString(), map);
            ProducerRecord<String, Event> record = new ProducerRecord<>("VisitorsTracking", event.getEventId(), event);
            eventProducer.send(record);
            Thread.sleep(50);
        }

        eventProducer.close();
    }
}
