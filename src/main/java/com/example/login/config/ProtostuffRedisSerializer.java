package com.example.login.config;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ProtostuffRedisSerializer <T> implements RedisSerializer<T> {
    private final RuntimeSchema<T> schema;

    public ProtostuffRedisSerializer(Class<T> type) {
        this.schema = RuntimeSchema.createFrom(type);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return ProtostuffIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        T instance = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, instance, schema);
        return instance;
    }
}
