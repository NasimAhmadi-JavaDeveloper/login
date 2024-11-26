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
            + " u.id AS userId,"
            + " u.userName AS userName,"
            + " SIZE(u.posts) AS totalPosts,"
            + " SIZE(u.comments) AS totalComments,"
            + " SIZE(u.followers) AS totalFollowers,"
            + " SIZE(u.following) AS totalFollowing) "
            + " FROM User u"
            + " GROUP BY u.id")
    Page<UserReportResponse> getUserReports(Pageable pageable);

    @Query(value = "SELECT new com.example.login.model.response.UserReportResponse ("
            + " u.id AS userId,"
            + " u.userName AS userName,"
            + " SIZE(u.posts) AS totalPosts,"
            + " SIZE(u.comments) AS totalComments,"
            + " SIZE(u.followers) AS totalFollowers,"
            + " SIZE(u.following) AS totalFollowing) "
            + " FROM User u"
            + " WHERE LOWER(u.userName) LIKE LOWER(CONCAT('%', :searchKey, '%')) "
            + " OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchKey, '%')) "
            + " GROUP BY u.id")
    Page<UserReportResponse> getUserReportsSearchKey(Pageable pageable ,@Param("searchKey") String searchKey);





}

