package com.events.processor.input;

import com.events.processor.input.domain.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EventProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.events.processor.serializer.EventSerializer");

        Producer<String, Event> eventProducer = new KafkaProducer<>(props);
        Map<String, Integer> experimentMap = new HashMap<>();
        experimentMap.put("experiment1", 1);
        experimentMap.put("experiment2", 2);
        Event event = new Event("1", "a", experimentMap);
        eventProducer.send(new ProducerRecord<>("TrackingEvents", event.getEventId(), event));
        eventProducer.close();

    }
}
