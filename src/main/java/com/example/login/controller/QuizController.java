package com.example.login.controller;

import com.example.login.model.request.QuizRequest;
import com.example.login.model.response.QuizResponse;
import com.example.login.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public QuizResponse create(@RequestBody QuizRequest request) {
        return quizService.create(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public QuizResponse findById(@PathVariable Integer id) {
        return quizService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<QuizResponse> findAll() {
        return quizService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public QuizResponse update(@PathVariable Integer id, @RequestBody QuizRequest request) {
        return quizService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        quizService.delete(id);
    }
}