package com.example.login.service;

import com.example.login.model.entity.exam.Question;
import com.example.login.model.entity.exam.Quiz;
import com.example.login.model.mapper.QuestionMapper;
import com.example.login.model.request.QuestionRequest;
import com.example.login.model.response.QuestionResponse;
import com.example.login.repository.QuestionRepository;
import com.example.login.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuizRepository quizRepository;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public QuestionResponse create(QuestionRequest request) {

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        Question question = questionMapper.toEntity(request);

        question.setQuiz(quiz); // 🔥 مهم‌ترین خط

        Question saved = questionRepository.save(question);

        return questionMapper.toResponse(saved);
    }

    public QuestionResponse findById(Integer id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        return questionMapper.toResponse(question);
    }

    public List<QuestionResponse> findAllByQuiz(Integer quizId) {

        return questionRepository.findByQuizIdOrderByQuestionOrderAsc(quizId)
                .stream()
                .map(questionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public QuestionResponse update(Integer id, QuestionRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        if (request.getQuizId() != null) {
            quizRepository.findById(request.getQuizId())
                    .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
        }

        questionMapper.update(question, request);

        return questionMapper.toResponse(questionRepository.save(question));
    }

    public void delete(Integer id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        questionRepository.delete(question);
    }
}
