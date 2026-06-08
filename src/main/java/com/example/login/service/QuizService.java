package com.example.login.service;

import com.example.login.model.entity.exam.Quiz;
import com.example.login.model.mapper.QuizMapper;
import com.example.login.model.request.QuizRequest;
import com.example.login.model.response.QuizResponse;
import com.example.login.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;

    public QuizResponse create(QuizRequest request) {

        if (quizRepository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("Quiz title already exists");//todo
        }

        Quiz saved = quizRepository.save(quizMapper.toEntity(request));

        return quizMapper.toResponse(saved);
    }

    public QuizResponse findById(Integer id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));//todo

        return quizMapper.toResponse(quiz);
    }

    public List<QuizResponse> findAll() {

        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toResponse)
                .collect(Collectors.toList());
    }

    public QuizResponse update(Integer id, QuizRequest request) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));//todo

        quizMapper.updateEntity(request, quiz);

        return quizMapper.toResponse(quizRepository.save(quiz));
    }

    public void delete(Integer id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));//todo

        quizRepository.delete(quiz);
    }
}
