package com.example.login.mapper;

import com.example.login.model.entity.Follow;
import com.example.login.model.response.FollowResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FollowMapper {

    @Mapping(target = "fromUsername", source = "from.userName")
    @Mapping(target = "toUsername", source = "to.userName")
    FollowResponse toDto(Follow follow);

    @Mapping(target = "from.userName", source = "fromUsername")
    @Mapping(target = "to.userName", source = "toUsername")
    Follow toEntity(FollowResponse followResponseDto);

    List<FollowResponse> toDtoList(List<Follow> follows);
}