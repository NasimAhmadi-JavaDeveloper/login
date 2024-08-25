package com.example.login.mapper;

import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T12:08:22+0330",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest request, String password) {
        if ( request == null && password == null ) {
            return null;
        }

        User user = new User();

        if ( request != null ) {
            user.setUserName( request.getUserName() );
            user.setEmail( request.getEmail() );
            user.setPhone( request.getPhone() );
            user.setRole( request.getRole() );
        }
        if ( password != null ) {
            user.setPassword( password );
        }

        return user;
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUserName( user.getUserName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setRole( user.getRole() );

        return userResponse;
    }
}
