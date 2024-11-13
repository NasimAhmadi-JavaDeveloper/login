package com.example.login.mapper;

import com.example.login.model.entity.User;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lockTimeDuration", ignore = true)
    @Mapping(target = "failedLoginAttempts", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "stringToCharArray")
    @Mapping(target = "version", ignore = true)
    User toEntity(UserRequest request, String password);

    @Named("stringToCharArray")
    default char[] mapStringToCharArray(String password) {
        return password != null ? password.toCharArray() : null;
    }

    UserResponse toResponse(User user);
}