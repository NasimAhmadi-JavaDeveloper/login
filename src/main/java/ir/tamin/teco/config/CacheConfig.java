package ir.tamin.teco.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    private static final int CONFIG = 600;//TODO
    private static final int SERVICE_TOKEN = 3550;//TODO

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.registerCustomCache("config",
                buildCache(CONFIG));

        cacheManager.registerCustomCache("optimusServiceToken",
                buildCache(SERVICE_TOKEN));

        return cacheManager;
    }

    private Cache<Object, Object> buildCache(int ttlSeconds) {
        return Caffeine.newBuilder()
                .expireAfterWrite(ttlSeconds, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .recordStats()
                .build();
    }
}
