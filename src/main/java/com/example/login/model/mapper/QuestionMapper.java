package com.example.login.model.mapper;

import com.example.login.model.entity.exam.Question;
import com.example.login.model.request.QuestionRequest;
import com.example.login.model.response.QuestionResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "quiz.id", source = "quizId")
    Question toEntity(QuestionRequest request);

    @Mapping(target = "quizId", source = "quiz.id")
    QuestionResponse toResponse(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "quiz.id", source = "quizId")
    void update(@MappingTarget Question question, QuestionRequest request);

}