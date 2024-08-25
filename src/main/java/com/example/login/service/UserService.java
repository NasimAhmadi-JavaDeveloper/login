package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.UserMapper;
import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void addUser(UserRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresent(u -> {
                    throw new LogicalException(ExceptionSpec.CONSTRAINT);
                });
        User user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public UserResponse getCurrentUser(Integer id) {
        return userMapper.toResponse(getUser(id));
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }
}
