package com.example.login.repository;

import com.example.login.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "posts")
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserWithPosts(@Param("id") Long id);
}