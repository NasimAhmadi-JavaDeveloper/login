package com.example.login.controller;

import com.example.login.model.request.CommentEmojiRequest;
import com.example.login.model.request.CommentRequest;
import com.example.login.model.response.CommentResponse;
import com.example.login.service.CommentService;
import com.example.login.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
@Tag(name = "Comments", description = "APIs for managing comments and emojis")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get comments for a specific post")
    @GetMapping("comment/{postId}")
    public Page<CommentResponse> getComment(@PathVariable("postId") long postId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        return commentService.getComments(postId, page, size);
    }

    @Operation(summary = "Add a new comment to a specific post by the current user.")
    @PostMapping("comment/{postId}")
    public void sendComment(@PathVariable("postId") long postId, @RequestBody @Valid CommentRequest request) {
        commentService.sendComment(Utils.getCurrentUserId(), postId, request.getComment(), null);
    }

    @Operation(summary = "React with an emoji to a post")
    @PostMapping("emoji/{postId}")
    public void sendEmoji(@PathVariable("postId") long postId, @RequestBody @Valid CommentEmojiRequest request) {
        commentService.sendComment(Utils.getCurrentUserId(), postId, null, request.getEmoji());
    }

    @Operation(summary = "Delete a comment")
    @DeleteMapping("comment")
    public void deleteComment(@RequestParam long commentId) {
        commentService.removeComment(Utils.getCurrentUserId(), commentId);
    }
}