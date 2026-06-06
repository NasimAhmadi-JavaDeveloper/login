package com.example.login.model.mapper;

import com.example.login.model.entity.ForbiddenWord;
import com.example.login.model.request.ForbiddenWordRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ForbiddenWordMapper {

    ForbiddenWord mapToEntity(ForbiddenWordRequest request);
}
