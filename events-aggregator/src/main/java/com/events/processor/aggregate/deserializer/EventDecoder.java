package com.events.processor.aggregate.deserializer;

import com.events.processor.domain.Event;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;

public class EventDecoder implements Decoder<Event> {

    public EventDecoder(VerifiableProperties props) {
    }

    @Override
    public Event fromBytes(byte[] bytes) {
        Event event = new Event();
        ProtostuffIOUtil.mergeFrom(bytes, event, RuntimeSchema.getSchema(Event.class));
        return event;
    }
}
