package com.example.login.mapper;

import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lockTimeDuration", ignore = true)
    @Mapping(target = "failedLoginAttempts", ignore = true)
    @Mapping(target = "profilePicture", ignore = true)
    @Mapping(target = "bio", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "follows", ignore = true)
    @Mapping(target = "followings", ignore = true)
    User toEntity(UserRequest request, String password);


    UserResponse toResponse(User user);
}