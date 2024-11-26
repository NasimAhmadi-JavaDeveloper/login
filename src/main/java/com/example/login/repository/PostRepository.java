package com.example.login.repository;

import com.example.login.model.dto.PostStatsDto;
import com.example.login.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.example.login.model.dto.PostStatsDto(" +
            " HOUR(p.createdAt), COALESCE(COUNT(p),0)) " +
            " FROM Post p " +
            " GROUP BY HOUR(p.createdAt) " +
            " order by HOUR(p.createdAt)")
    List<PostStatsDto> countPostsByHour();
}