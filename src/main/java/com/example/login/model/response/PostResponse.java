package com.example.login.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private Long userId;
    private String caption;
    private List<String> imageUrls;
    private Integer visitCount;
    private List<String> tag;
}
