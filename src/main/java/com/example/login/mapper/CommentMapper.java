package com.example.login.mapper;

import com.example.login.model.entity.Comment;
import com.example.login.model.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "post.caption", target = "postCaption")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "createdAt", target = "createdAt")
    CommentResponse toCommentResponse(Comment comment);
}
