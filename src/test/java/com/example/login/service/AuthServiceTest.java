package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.entity.User;
import com.example.login.model.response.LoginResponse;
import com.example.login.repository.UserRepository;
import com.example.login.security.JWTService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTService jwtService;
    @InjectMocks
    private AuthService authService;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(userRepository, userService, passwordEncoder, jwtService);
    }

    @Test
    public void login_SuccessfulLogin_ReturnsToken() {

        //GIVEN
        user = new User();
        user.setUserName("testUser");
        user.setPassword("1375");
        user.setFailedLoginAttempts(0);
        user.setLocked(false);
        user.setLockTime(null);

        //WHEN
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "1375")).thenReturn(true);
        when(jwtService.generateEncodeAccessToken(user)).thenReturn("jwtToken");
        LoginResponse response = authService.login("testUser", "password");

        //THEN
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
        verify(userService).resetFailedAttempts("testUser");
        verify(userService, never()).loginFailed(any());
    }

    @Test
    public void login_UserLocked_ThrowsLogicalException() {

        //GIVEN
        user = new User();
        user.setUserName("testUser");
        user.setPassword("1375");
        user.setFailedLoginAttempts(0);
        user.setLocked(true);
        user.setLockTime(LocalDateTime.now().minusMinutes(10));

        //WHEN
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(userService.unlockWhenTimeExpired(user)).thenReturn(false);
        LogicalException thrown = assertThrows(LogicalException.class, () ->
                authService.login("testUser", "password")
        );

        //THEN
        assertEquals(ExceptionSpec.USER_LOCKED.getMessage(), thrown.getMessage());
        verify(userService, never()).resetFailedAttempts("testUser");
        verify(jwtService, never()).generateEncodeAccessToken(user);
        verify(userService, never()).loginFailed(user);
    }

    @Test
    public void login_InvalidPassword_ThrowsLogicalException() {

        //GIVEN
        user = new User();
        user.setUserName("testUser");
        user.setPassword("1375");
        user.setFailedLoginAttempts(0);
        user.setLocked(false);
        user.setLockTime(null);

        //WHEN
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        LogicalException thrown = assertThrows(LogicalException.class, () ->
                authService.login("testUser", "wrongPassword")
        );

        //THEN
        assertEquals(ExceptionSpec.UN_AUTHORIZED.getMessage(), thrown.getMessage());
        verify(userService).loginFailed(user);
    }

    @Test
    public void login_UserNotFound_ThrowsUserNotFoundException() {

        //WHEN
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());
        LogicalException thrown = assertThrows(LogicalException.class,
                () -> authService.login("testUser", "password")
        );

        //THEN
        assertEquals(ExceptionSpec.USER_NOT_FOUND.getMessage(), thrown.getMessage());
        verify(userService, never()).resetFailedAttempts(any());
        verify(userService, never()).loginFailed(any());
    }

    @Test
    public void login_UserLockedButUnlockSuccess_UnlocksAndLogsIn() {

        //GIVEN
        user = new User();
        user.setUserName("testUser");
        user.setPassword("1375");
        user.setFailedLoginAttempts(3);
        user.setLocked(true);
        user.setLockTime(LocalDateTime.now().minusMinutes(40));

        //WHEN
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(userService.unlockWhenTimeExpired(user)).thenReturn(true);
        when(passwordEncoder.matches("1375", user.getPassword())).thenReturn(true);
        when(jwtService.generateEncodeAccessToken(user)).thenReturn("jwtToken");
        LoginResponse response = authService.login("testUser", user.getPassword());

        //THEN
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
        verify(userService).resetFailedAttempts("testUser");
        verify(userService, never()).loginFailed(any());
    }
}