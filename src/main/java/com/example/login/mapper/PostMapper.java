package com.example.login.mapper;

import com.example.login.model.entity.Post;
import com.example.login.model.request.PostRequest;
import com.example.login.model.response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PostMapper {
    @Mapping(target = "user.id", source = "userId")
    Post toEntity(PostRequest.PostCreateDto dto);

    @Mapping(target = "userId", source = "user.id")
    PostResponse toDto(Post post);
}