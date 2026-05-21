package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.ForbiddenWordMapper;
import com.example.login.model.entity.ForbiddenWord;
import com.example.login.model.request.ForbiddenWordRequest;
import com.example.login.repository.ForbiddenWordRepository;
import com.example.login.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ForbiddenWordService {

    private final ForbiddenWordMapper forbiddenWordMapper;
    private final ForbiddenWordRepository forbiddenWordRepository;

    public void addForbiddenWord(ForbiddenWordRequest request) {

        if (forbiddenWordRepository.existsByWordIgnoreCase(request.getWord())) {
            throw new LogicalException(ExceptionSpec.DUPLICATE_FORBIDDEN_WORD);
        }

        ForbiddenWord saved = forbiddenWordRepository.save(forbiddenWordMapper.mapToEntity(request));

        log.info("User {} added forbidden word: {}", Utils.getCurrentUserId(), saved.getWord());
    }

    public void deleteForbiddenWord(Integer id) {

        ForbiddenWord forbiddenWord = forbiddenWordRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.FORBIDDEN_WORD_NOT_FOUND));

        forbiddenWordRepository.delete(forbiddenWord);

        log.info("User {} deleted forbidden word: {}", Utils.getCurrentUserId(), forbiddenWord.getWord());
    }

    public boolean containsForbiddenWord(String text) {

        List<ForbiddenWord> forbiddenWords = forbiddenWordRepository.findAll();

        if (forbiddenWords.isEmpty()) {
            return false;
        }

        String lowerCaseText = text.toLowerCase();

        return forbiddenWords.stream()
                .anyMatch(fw -> lowerCaseText.toLowerCase().contains(fw.getWord().toLowerCase()));
    }
}