package com.example.login.model.proj;

import com.example.login.model.entity.User;

import java.time.LocalDate;

public interface LikeProjection {
    LocalDate getLikeDate();
    User getUser();
}