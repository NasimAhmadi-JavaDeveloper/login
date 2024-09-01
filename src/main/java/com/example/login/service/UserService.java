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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION = 30 * 60 * 1000; // 30 minutes in milliseconds
    private static final int ZERO = 0;

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

    public void loginFailed(User user) {
        int newFailAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newFailAttempts);

        if (newFailAttempts >= MAX_FAILED_ATTEMPTS) {
            user.setLocked(true);
            user.setLockTime(LocalDateTime.now());
        }

        userRepository.save(user);
    }

    public void resetFailedAttempts(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        user.ifPresent(u -> {
            u.setFailedLoginAttempts(ZERO);
            u.setLocked(false);
            u.setLockTime(null);
            userRepository.save(u);
        });
    }

    public boolean unlockWhenTimeExpired(User user) {
        if (user.isLocked() && user.getLockTime() != null) {
            LocalDateTime lockTime = user.getLockTime();
            LocalDateTime now = LocalDateTime.now();

            if (lockTime.plusMinutes(LOCK_TIME_DURATION).isBefore(now)) {
                user.setLocked(false);
                user.setLockTime(null);
                user.setFailedLoginAttempts(ZERO);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
