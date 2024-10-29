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
    public void successfulLogin_ReturnsToken() {

        //GIVEN Entity
        user = new User();
        user.setUserName("nasim");
        user.setPassword("1375".toCharArray());
        user.setFailedLoginAttempts(0);
        user.setLockTimeDuration(null);

        //GIVEN DTO
        String sentUserName = "nasim";
        char[] sentPassword = "1375".toCharArray();

        //WHEN
        when(userService.getUserByName(sentUserName))
                .thenReturn(user);

        when(userService.isUserLocked(user))
                .thenReturn(true);

        when(passwordEncoder.matches(String.valueOf(sentPassword), String.valueOf(user.getPassword())))
                .thenReturn(true);

        when(jwtService.generateEncodeAccessToken(user))
                .thenReturn("jwtToken");

        LoginResponse response = authService.login(user.getUserName(), sentPassword);

        //THEN
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
      //  verify(userService).resetFailedAttempts(sentUserName);
       // verify(userService, never()).loginFailed(any());
    }

    @Test
    public void userLocked_ThrowsUserLockedException() {

        //GIVEN Entity
        user = new User();
        user.setUserName("nasim");
        user.setPassword("1375".toCharArray());
        user.setFailedLoginAttempts(0);
        user.setLockTimeDuration(LocalDateTime.now().minusMinutes(10));

        //GIVEN DTO
        String sentUserName = "nasim";
        char[] sentPassword = "1375".toCharArray();

        //WHEN
        when(userService.getUserByName(sentUserName))
                .thenReturn(user);

        when(userService.isUserLocked(user))
                .thenReturn(false);

        LogicalException thrown = assertThrows(LogicalException.class,
                () -> authService.login(user.getUserName(), sentPassword)
        );

        //THEN
        assertEquals(ExceptionSpec.USER_LOCKED.getMessage(), thrown.getMessage());
//        verify(userService, never())
//                .resetFailedAttempts(sentUserName);

        verify(jwtService, never())
                .generateEncodeAccessToken(user);

//        verify(userService, never())
//                .loginFailed(user);
    }

    @Test
    public void invalidPassword_ThrowsUnauthorizedException() {

        //GIVEN Entity
        user = new User();
        user.setUserName("nasim");
        user.setPassword("1375".toCharArray());
        user.setFailedLoginAttempts(2);
        user.setLockTimeDuration(null);

        //GIVEN DTO
        String sentUserName = "nasim";
        char[] sentPassword = "55889966".toCharArray();

        //WHEN
        when(userService.getUserByName(sentUserName))
                .thenReturn(user);

        when(userService.isUserLocked(user))
                .thenReturn(true);

        when(passwordEncoder.matches(String.valueOf(sentPassword), String.valueOf(user.getPassword())))
                .thenReturn(false);

        LogicalException thrown = assertThrows(LogicalException.class,
                () -> authService.login(user.getUserName(), sentPassword)
        );

        //THEN
        assertEquals(ExceptionSpec.UN_AUTHORIZED.getMessage(), thrown.getMessage());
       // verify(userService).loginFailed(user);
    }

    @Test
    public void userLockedButUnlockSuccess_UnlocksAndLogIn() {

        //GIVEN Entity
        User user = new User();
        user.setUserName("nasim");
        user.setPassword("1375".toCharArray());
        user.setFailedLoginAttempts(3);
        user.setLockTimeDuration(LocalDateTime.now().minusMinutes(40));

        //GIVEN DTO
        String sentUserName = "nasim";
        char[] sentPassword = "1375".toCharArray();

        //WHEN
        when(userService.getUserByName(sentUserName))
                .thenReturn(user);

        when(userService.isUserLocked(user))
                .thenReturn(true);

        when(passwordEncoder.matches(String.valueOf(sentPassword), String.valueOf(user.getPassword())))
                .thenReturn(true);

        when(jwtService.generateEncodeAccessToken(user)).thenReturn("jwtToken");
        LoginResponse response = authService.login(sentUserName, sentPassword);

        //THEN
        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
      //  verify(userService).resetFailedAttempts(sentUserName);
        verify(jwtService).generateEncodeAccessToken(user);
      //  verify(userService, never()).loginFailed(any());
    }

}