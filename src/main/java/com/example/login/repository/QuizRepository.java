package com.example.login.repository;

import com.example.login.model.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    boolean existsByTitle(String title);
}
