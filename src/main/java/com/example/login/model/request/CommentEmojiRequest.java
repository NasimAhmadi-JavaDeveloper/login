package com.example.login.model.request;

import com.example.login.model.enums.Emoji;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentEmojiRequest {
    private Emoji emoji;
}