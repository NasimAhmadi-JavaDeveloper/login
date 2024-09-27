package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.UserMapper;
import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.max-failed-attempts:3}")
    public int maxFailedAttempts;
    @Value("${security.lock-time-duration-seconds:1800}")
    public long lockTimeDurationSeconds;
    private static final int ZERO = 0;

    public void addUser(UserRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresent(u -> {
                    throw new LogicalException(ExceptionSpec.CONSTRAINT);
                });

        if (Objects.isNull(request.getId())) {
            User user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
        } else {
            User user = userRepository.findById(request.getId())
                    .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()).toCharArray());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setRole(request.getRole());
            userRepository.save(user);
        }
    }

    public UserResponse getCurrentUser(Integer id) {
        return userMapper.toResponse(getUser(id));
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public void loginFailed(User user) {
        int newFailAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newFailAttempts);

        if (newFailAttempts >= maxFailedAttempts) {
            user.setLockTimeDuration(LocalDateTime.now().plusSeconds(lockTimeDurationSeconds));
        }

        userRepository.save(user);
    }

    public void resetFailedAttempts(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        user.ifPresent(u -> {
            u.setFailedLoginAttempts(ZERO);
            u.setLockTimeDuration(null);
            userRepository.save(u);
        });
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

}
