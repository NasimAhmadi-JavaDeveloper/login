package com.example.login.config;

import com.example.login.model.response.UserResponse;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class CacheConfig {
     @Bean
      public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

         RedisCacheConfiguration userByIdConfig = RedisCacheConfiguration
                 .defaultCacheConfig()
                 .entryTtl(Duration.ofHours(11111111))
                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                         new GenericJackson2JsonRedisSerializer()));

         RedisCacheConfiguration userByEmailConfig = RedisCacheConfiguration
                 .defaultCacheConfig()
                 .entryTtl(Duration.ofHours(11111111))
                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                         new ProtostuffRedisSerializer<>(UserResponse.class)));

         return RedisCacheManager.builder(redisConnectionFactory)
                 .withCacheConfiguration("userById", userByIdConfig)
                 .withCacheConfiguration("userByEmail", userByEmailConfig)
                 .build();
     }
}