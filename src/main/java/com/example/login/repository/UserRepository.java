package com.example.login.repository;

import com.example.login.model.entity.User;
import com.example.login.model.response.UserReportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "posts")
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserWithPosts(@Param("id") Long id);

    @Query(value = "SELECT new com.example.login.model.response.UserReportResponse ("
            + " u.id,"
            + " u.userName,"
            + " COALESCE(COUNT(l.id), 0),"
            + " SIZE(u.posts),"
            + " SIZE(u.comments),"
            + " SIZE(u.followers),"
            + " SIZE(u.following)) "
            + " FROM User u"
            + " LEFT JOIN u.posts p"
            + " LEFT JOIN p.likes l"
            + " GROUP BY u.id"
            + " ORDER BY COALESCE(COUNT(l.id), 0) DESC, SIZE(u.posts) DESC, SIZE(u.comments) DESC, SIZE(u.followers) DESC, SIZE(u.following) DESC")
    Page<UserReportResponse> getUserReports(Pageable pageable);

}