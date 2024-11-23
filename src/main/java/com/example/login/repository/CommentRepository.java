package com.example.login.repository;

import com.example.login.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c "
            + " where c.post.id = ?1  "
            + " and c.emoji is null "
            + " order by c.createdAt desc ")
    Page<Comment> findAllComment(long postId, Pageable pageable);
}