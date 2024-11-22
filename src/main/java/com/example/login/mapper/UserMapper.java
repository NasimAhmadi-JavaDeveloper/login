package com.example.login.mapper;

import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest request, String password);

    UserResponse toResponse(User user);
}