package com.example.login.service;

import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserRepository userRepository;

    @CacheEvict(value = "userById", key = "#id")
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}