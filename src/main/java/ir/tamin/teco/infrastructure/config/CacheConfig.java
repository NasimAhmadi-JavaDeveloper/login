package ir.tamin.teco.infrastructure.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  private static final int SECONDS_1_MIN = 60;
  private static final int SECONDS_2_MIN = 120;

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();

    cacheManager.registerCustomCache("config",
        buildCache(SECONDS_1_MIN));

    cacheManager.registerCustomCache("optimusServiceToken",
        buildCache(SECONDS_2_MIN));
    return cacheManager;
  }

  private com.github.benmanes.caffeine.cache.Cache<Object, Object> buildCache(int ttlSeconds) {
    return Caffeine.newBuilder()
        .expireAfterWrite(ttlSeconds, TimeUnit.SECONDS)
        .maximumSize(10_000)
        .recordStats()
        .build();
  }
}