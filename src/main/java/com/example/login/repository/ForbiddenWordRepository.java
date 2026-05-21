package com.example.login.repository;

import com.example.login.model.entity.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Integer> {

    boolean existsByWordIgnoreCase(String word);
}