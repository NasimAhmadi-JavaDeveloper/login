//package com.example.login.service;
//
//import com.example.login.exception.LogicalException;
//import com.example.login.mapper.AuthMapper;
//import com.example.login.model.response.AuthResponse;
//import com.example.login.model.entity.User;
//import com.example.login.model.request.AuthRequest;
//import com.example.login.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class AuthServiceTest {
//
//    @Mock
//    UserRepository userRepositoryMock;
//    @Mock
//    PasswordEncoder passwordEncoderMock;

//    @InjectMocks
//    AuthService authService;
//
//    @Test
//    void givenRequestWhenUserNameNotFoundThenThrowsLogicalException() {
//
//        //GIVEN
//        AuthRequest request = new AuthRequest()
//                .setPassword("123456")
//                .setUserName("sara");
//
//        //WHEN
//        when(userRepositoryMock.findByUserName(request.getUserName()))
//                .thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(LogicalException.class, () -> authService.login(request));
//    }
//
//    @Test
//    void givenRequestWhenUserNameFoundThenReturnResponse() {
//
//        //GIVEN
//        AuthRequest request = new AuthRequest()
//                .setPassword("123456")
//                .setUserName("sara");
//
//        User user = new User()
//                .setPassword("123456")
//                .setUserName("sara")
//                .setId(1)
//                .setPhone("09122222222");
//
//        AuthResponse response = new AuthResponse("dddddddddddddddd");
//
//        //WHEN
//        when(userRepositoryMock.findByUserName(request.getUserName()))
//                .thenReturn(Optional.of(user));
//
//        when(passwordEncoderMock.matches(request.getPassword(), user.getPassword()))
//                .thenReturn(true);
//
//        //THEN
//        assertEquals(response, authService.login(request));
//    }
//}