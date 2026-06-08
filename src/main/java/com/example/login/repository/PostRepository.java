package com.example.login.repository;

import com.example.login.model.dto.PostStatsDto;
import com.example.login.model.entity.socialmedia.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT new com.example.login.model.dto.PostStatsDto(" +
            " HOUR(p.createdAt), COALESCE(COUNT(p),0)) " +
            " FROM Post p " +
            " GROUP BY HOUR(p.createdAt) " +
            " order by HOUR(p.createdAt)")
    List<PostStatsDto> countPostsByHour();

    @Query("FROM Post p WHERE p.user.id = :userId")
    List<Post> findUserPosts(@Param("userId") int userId);

}