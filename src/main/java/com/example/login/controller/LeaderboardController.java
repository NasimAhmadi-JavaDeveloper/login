package com.example.login.controller;

import com.example.login.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @PostMapping("/addScore")
    public void addOrUpdateScore(@RequestParam String player, @RequestParam double score) {
        leaderboardService.addOrUpdateScore(player, score);
    }

    @GetMapping("/topPlayers")
    public Set<Object> getTopPlayers(@RequestParam int topN) {
        return leaderboardService.getTopPlayers(topN);
    }

    @GetMapping("/playerRank")
    public Long getPlayerRank(@RequestParam String player) {
        return leaderboardService.getPlayerRank(player);
    }

    @GetMapping("/playersByScore")
    public Set<Object> getPlayersByScoreRange(@RequestParam double minScore, @RequestParam double maxScore) {
        return leaderboardService.getPlayersByScoreRange(minScore, maxScore);
    }

    @PostMapping("/incrementScore")
    public Double incrementScore(@RequestParam String player, @RequestParam double scoreIncrement) {
        return leaderboardService.incrementScore(player, scoreIncrement);
    }
}