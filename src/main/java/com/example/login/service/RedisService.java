package com.example.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RedisService {

    @Value("${security.max-failed-attempts:3}")
    public int maxFailedAttempts;
    private static final String USER_LOCK_KEY = "user:lock:";
    private static final String USER_FAILED_ATTEMPTS_KEY = "user:failedAttempts:";

    private final RedisTemplate<String, Integer> redisTemplate;
    private static final long LOCK_TTL_MIN = 60;

    public boolean isUserLocked(String userName) {
        String lockKey = getKey(userName);
        return Boolean.TRUE.equals(redisTemplate.hasKey(lockKey));
    }

    public void increaseFailedAttempts(String userName) {
        Integer failedAttempts = redisTemplate.opsForValue().get(getKey(userName));
        int newFailAttempts = (failedAttempts == null) ? 1 : failedAttempts+1;
        if (newFailAttempts >= maxFailedAttempts) {
            lockUser(userName);
        }
        redisTemplate.opsForValue().set(getKey(userName), newFailAttempts);
    }

    private static String getKey(String userName) {
        return USER_FAILED_ATTEMPTS_KEY + userName;
    }

    public void resetFailedAttempts(String username) {
        String failedAttemptsKey = getKey(username);
        String lockKey = USER_LOCK_KEY + username;
        redisTemplate.delete(failedAttemptsKey);
        redisTemplate.delete(lockKey);
    }

    public void lockUser(String userName) {
        String lockKey = USER_LOCK_KEY + userName;
        redisTemplate.opsForValue().set(lockKey, 1, LOCK_TTL_MIN, TimeUnit.MINUTES);
        log.info("User {} has been locked for {} seconds", userName, LOCK_TTL_MIN);
    }
}