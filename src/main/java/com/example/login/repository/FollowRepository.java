package com.example.login.repository;

import com.example.login.model.entity.Follow;
import com.example.login.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFrom(User from);

    List<Follow> findByTo(User to);

    boolean existsByFromAndTo(User from, User to);

    Optional<Follow> findByFromAndTo(User fromUser, User toUser);
}
