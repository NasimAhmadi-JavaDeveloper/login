package com.example.login.model.response;

import com.example.login.model.enums.Emoji;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long postId;
    private String postCaption;
    private Integer userId;
    private String userName;
    private String commentText;
    private Emoji emoji;
}