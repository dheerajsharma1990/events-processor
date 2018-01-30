package com.events.processor.serializer;

import com.events.processor.input.domain.Event;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class EventSerializer implements Serializer<Event> {

    private static final int bufferSize = 2048;

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }


    @Override
    public void close() {

    }

    @Override
    public byte[] serialize(String arg0, Event event) {
        try {
            Schema schema = RuntimeSchema.getSchema(event.getClass());
            return ProtostuffIOUtil.toByteArray(event, schema,
                    getApplicationBuffer());
        } finally {
            getApplicationBuffer().clear();
        }
    }

    private static LinkedBuffer getApplicationBuffer() {
        return localBuffer.get();
    }

    private static final ThreadLocal<LinkedBuffer> localBuffer = new ThreadLocal<LinkedBuffer>() {
        public LinkedBuffer initialValue() {
            return LinkedBuffer.allocate(bufferSize);
        }
    };


}
