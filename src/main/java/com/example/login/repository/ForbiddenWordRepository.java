package com.example.login.repository;

import com.example.login.model.entity.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Integer> {

    boolean existsByWordIgnoreCase(String word);

    boolean existsByWordIn(Collection<String> word);
}