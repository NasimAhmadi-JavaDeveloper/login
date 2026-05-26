package com.example.login.repository;

import com.example.login.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    @Query("SELECT u.blocked FROM UserDetail u WHERE u.id = :userId")
    Optional<Boolean> isUserBlocked(@Param("userId") Integer id);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE UserDetail u SET u.banWordCount = :newBanWord WHERE u.id = :userId")
    void incBanWordCount(@Param("userId") Integer userId, @Param("newBanWord") Integer newBanWord);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("UPDATE UserDetail u SET u.blocked = true WHERE u.id = :userId")
    void blockUser(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserDetail u SET u.blocked = false WHERE u.id = :userId")
    void unblockUser(@Param("userId") Integer userId);

}