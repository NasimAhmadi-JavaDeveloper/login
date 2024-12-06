package com.example.login.repository;

import com.example.login.model.dto.PostStatsDto;
import com.example.login.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.example.login.model.dto.PostStatsDto(" +
            " HOUR(p.createdAt), COALESCE(COUNT(p),0)) " +
            " FROM Post p " +
            " GROUP BY HOUR(p.createdAt) " +
            " order by HOUR(p.createdAt)")
    List<PostStatsDto> countPostsByHour();

    @Query("FROM Post p WHERE p.user.id = :userId")
    List<Post> findUserPosts(@Param("userId") Long userId);

    @Query("SELECT COUNT(DISTINCT FUNCTION('DATE', p.createdAt))" +
            "FROM Post p " +
            "JOIN p.likes l " +
            "WHERE p.user.id = :userId " +
            "AND l.id <> :userId " +
            "AND p.createdAt BETWEEN :startDate AND :endDate")
    long countDistinctLikedDatesByUserAndDateRange(@Param("userId") Integer userId,
                                                   @Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);
}