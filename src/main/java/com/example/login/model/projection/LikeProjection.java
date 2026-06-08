package com.example.login.model.projection;

import com.example.login.model.entity.user.User;

import java.time.LocalDate;

public interface LikeProjection {
    LocalDate getLikeDate();
    User getUser();
}