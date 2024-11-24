package com.example.login.mapper;

import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", source = "password")
    User toEntity(UserRequest request, String password);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}