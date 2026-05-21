package com.example.login.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForbiddenWordResponse {
    private Integer id;
    private String word;
}