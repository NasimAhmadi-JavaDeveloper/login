package com.example.login.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionRequest {
    private String title;
    private Integer quizId;
    private Integer questionOrder;
}
