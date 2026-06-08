package com.example.login.repository;

import com.example.login.model.entity.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuizIdOrderByQuestionOrderAsc(Integer quizId);
}