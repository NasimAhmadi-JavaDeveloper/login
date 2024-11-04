package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.UserMapper;
import com.example.login.model.entity.User;
import com.example.login.model.request.PatchUserRequest;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import com.example.login.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SaveUserService saveUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    @Value("${security.lock-time-duration-seconds:1800}")
    public long lockTimeDurationSeconds;
    private static final int ZERO = 0;

    public void addUser(UserRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresent(u -> {
                    throw new LogicalException(ExceptionSpec.CONSTRAINT);
                });

        if (Objects.isNull(request.getId())) {
            saveUserService.saveUser(request);
        } else {
            updateUserService.updateUser(request);
        }
    }

    public void deleteUser(Integer id) {
        deleteUserService.deleteUser(id);
    }

    @Cacheable(value = "userById", key = "#id", unless = "#result == null")
    public UserResponse findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    @Cacheable(value = "userByEmail", key = "#email", unless = "#result == null")
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public UserResponse getCurrentUser(Integer id) {
        return userMapper.toResponse(getUser(id));
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public User getUserByEmail(int userId, String email) {
        return userRepository.findByIdAndEmail(userId, email)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public boolean isUserLocked(User user) {
        if (user.getLockTimeDuration() == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(user.getLockTimeDuration())) {
            user.setFailedLoginAttempts(ZERO);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public void patchUser(Integer id, String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
        mapper.readValue(json, PatchUserRequest.class);
        mapper.readerForUpdating(user).readValue(json);
        userRepository.save(user);
    }

//    public Integer createUserIfEmailNotExists(String email) {
//        boolean b = userRepository.existsByEmail(email);
//        if(b){
//            User user = userRepository.findByEmail(email).get();
//
//        }
//
//        if (!) {
//            User entity = User.builder()
//                    .email(email)
//                    .role(Role.ROLE_USER)
//                    .phone("95178308197")
//                    .userName("system")
//                    .password("system".toCharArray())
//                    .failedLoginAttempts(ZERO)
//                    .build();
//            User saved = userRepository.save(entity);
//            return saved.getId();
//        }
//    }

    public String getUserEmailById(int userId) {
        return userRepository.findById(userId)
                .map(User::getEmail)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }
}