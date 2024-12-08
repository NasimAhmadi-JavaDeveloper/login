package com.example.login.repository;

import com.example.login.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c "
            + " WHERE c.post.id = ?1 "
            + " ORDER BY c.createdAt DESC ")
    Page<Comment> findAllComment(long postId, Pageable pageable);

    @Query("FROM Comment c WHERE c.post.id = :postId")
    List<Comment> findCommentByPostId(long postId);

}