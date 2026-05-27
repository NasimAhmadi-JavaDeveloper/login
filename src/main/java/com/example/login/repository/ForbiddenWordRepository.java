package com.example.login.repository;

import com.example.login.model.entity.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Integer> {

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM ForbiddenWord w WHERE LOWER(w.word) = LOWER(:word)")
    boolean existsByWordIgnoreCase(String word);
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM ForbiddenWord w WHERE LOWER(w.word) IN :words")
    boolean existsByWordIgnoreCaseIn(Collection<String> words);
}