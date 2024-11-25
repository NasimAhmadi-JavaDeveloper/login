package com.example.login.service;

import com.example.login.model.entity.User;
import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserRepository userRepository;

    //@CacheEvict(value = "userById", key = "#id")
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        for (User follower : user.getFollowers()) {
            follower.getFollowing().remove(user);
        }

        for (User following : user.getFollowing()) {
            following.getFollowers().remove(user);
        }

        user.getFollowers().clear();
        user.getFollowing().clear();
        userRepository.delete(user);
    }
}