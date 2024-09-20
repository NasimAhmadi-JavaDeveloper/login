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
    private static final long LOCK_TIME_DURATION_SECONDS = 30 * 60;
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
            user.setLockTimeDuration(LocalDateTime.now().plusMinutes(30));
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

    public boolean unlockWhenTimeExpired(User user) {
        if (user.getLockTimeDuration() != null) {
            LocalDateTime lockTime = user.getLockTimeDuration();
            LocalDateTime now = LocalDateTime.now();

            if (lockTime.plusSeconds(LOCK_TIME_DURATION_SECONDS).isBefore(now)) {
                user.setLockTimeDuration(null);
                user.setFailedLoginAttempts(ZERO);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }
}
