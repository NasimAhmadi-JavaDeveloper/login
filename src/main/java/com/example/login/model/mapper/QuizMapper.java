package com.example.login.model.mapper;

import com.example.login.model.entity.exam.Quiz;
import com.example.login.model.request.QuizRequest;
import com.example.login.model.response.QuizResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    Quiz toEntity(QuizRequest request);

    QuizResponse toResponse(Quiz quiz);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(QuizRequest request, @MappingTarget Quiz quiz);

}