package com.example.login.service;

import com.example.login.mapper.CommentMapper;
import com.example.login.model.entity.Comment;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.enums.Emoji;
import com.example.login.model.response.CommentResponse;
import com.example.login.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void sendComment(int userId, long postId, String comment, Emoji emoji) {
         User user = userService.getUser(userId);
         Post post = postService.getPost(postId);

        commentRepository.save(new Comment()
                .setCommentText(comment)
                .setEmoji(emoji)
                .setPost(post)
                .setUser(user));
    }

    public void removeComment(int userId, long commentId) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment_not_found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not_your_comment");
        }
        commentRepository.delete(comment);
    }

    public Page<CommentResponse> getComments(long postId, int page, int size) {
        return commentRepository.findAllComment(postId, PageRequest.of(page, size))
                .map(commentMapper::toCommentResponse);
    }
}