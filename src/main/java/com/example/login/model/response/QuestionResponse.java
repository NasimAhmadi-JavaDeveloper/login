package com.example.login.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionResponse {
    private Integer id;
    private String title;
    private Integer quizId;
    private Integer questionOrder;
}
