package com.example.login.controller;

import com.example.login.model.response.FollowResponse;
import com.example.login.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/follow")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow")
    public ResponseEntity<Void> followUser(@RequestParam int fromUserId, @RequestParam int toUserId) {
        followService.followUser(fromUserId, toUserId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestParam int fromUserId, @RequestParam int toUserId) {
        followService.unfollowUser(fromUserId, toUserId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<List<FollowResponse>> getFollowings(@PathVariable int userId) {
        return ResponseEntity.ok(followService.getFollowings(userId));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowResponse>> getFollowers(@PathVariable int userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

}
