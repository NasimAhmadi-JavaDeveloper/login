package com.example.login.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReportResponse {
    private Number userId;
    private String userName;
    private Number totalPosts;
    private Number totalComments;
    private Number totalFollowers;
    private Number totalFollowing;
}