package com.example.login.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizResponse {
    private Integer id;
    private String title;
    private boolean done;
}