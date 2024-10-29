package com.example.login.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String LEADERBOARD_KEY = "game_leaderboard";

    public LeaderboardService(@Qualifier("leaderBoardRedisTemplate") RedisTemplate<String, Object> leaderBoardRedisTemplate) {
        this.redisTemplate = leaderBoardRedisTemplate;
    }

    public void addOrUpdateScore(String player, double score) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, player, score);
    }

    public Set<Object> getTopPlayers(int topN) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        return zSetOps.reverseRange(LEADERBOARD_KEY, 0, topN - 1);
    }

    public Long getPlayerRank(String player) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY, player);
        return rank != null ? rank + 1 : null;
    }

    public Set<Object> getPlayersByScoreRange(double minScore, double maxScore) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        return zSetOps.rangeByScore(LEADERBOARD_KEY, minScore, maxScore);
    }

    public Double incrementScore(String player, double scoreIncrement) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        return zSetOps.incrementScore(LEADERBOARD_KEY, player, scoreIncrement);
    }

}
