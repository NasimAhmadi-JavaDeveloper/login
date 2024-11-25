package com.example.login.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReportResponse {
    private Integer userId;
    private String userName;
    private Long totalLikes;
    private Integer totalPosts;
    private Integer totalComments;
    private Integer totalFollowers;
    private Integer totalFollowing;
}