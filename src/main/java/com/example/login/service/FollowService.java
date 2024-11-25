package com.example.login.service;

import com.example.login.mapper.FollowMapper;
import com.example.login.model.entity.Follow;
import com.example.login.model.entity.User;
import com.example.login.model.response.FollowResponse;
import com.example.login.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final UserService userService;
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;

    public void followUser(int fromUserId, int toUserId) {
        User fromUser = userService.getUser(fromUserId);
        User toUser = userService.getUser(toUserId);

        if (fromUserId == toUserId) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }

        if (followRepository.existsByFromAndTo(fromUser, toUser)) {
            throw new IllegalArgumentException("Already following this user.");
        }

        Follow follow = new Follow()
                .setFrom(fromUser)
                .setTo(toUser);
        followRepository.save(follow);
    }

    public void unfollowUser(int fromUserId, int toUserId) {
        User fromUser = userService.getUser(fromUserId);
        User toUser = userService.getUser(toUserId);

        Follow follow = followRepository.findByFromAndTo(fromUser, toUser)
                .orElseThrow(() -> new EntityNotFoundException("Follow relationship not found."));

        followRepository.delete(follow);
    }

    public List<FollowResponse> getFollowings(int userId) {
        User user =  userService.getUser(userId);
        return followRepository.findByFrom(user)
                .stream()
                .map(followMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FollowResponse> getFollowers(int userId) {
        User user = userService.getUser(userId);
        return followRepository.findByTo(user)
                .stream()
                .map(followMapper::toDto)
                .collect(Collectors.toList());
    }
}