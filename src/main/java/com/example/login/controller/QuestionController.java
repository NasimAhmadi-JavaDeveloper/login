package com.example.login.controller;

import com.example.login.model.request.QuestionRequest;
import com.example.login.model.response.QuestionResponse;
import com.example.login.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionResponse create(@RequestBody QuestionRequest request) {
        return questionService.create(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionResponse findById(@PathVariable Integer id) {
        return questionService.findById(id);
    }

    @GetMapping("/quiz/{quizId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<QuestionResponse> findByQuiz(@PathVariable Integer quizId) {
        return questionService.findAllByQuiz(quizId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionResponse update(@PathVariable Integer id, @RequestBody QuestionRequest request) {
        return questionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        questionService.delete(id);
    }
}