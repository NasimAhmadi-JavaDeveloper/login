package com.example.login.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponse {
    private Integer id;
    private String fromUsername;
    private String toUsername;
}
