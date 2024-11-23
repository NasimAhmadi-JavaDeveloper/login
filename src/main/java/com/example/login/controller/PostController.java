package com.example.login.controller;

import com.example.login.model.request.PostRequest;
import com.example.login.model.response.PostResponse;
import com.example.login.service.PostService;
import com.example.login.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest.PostCreateDto dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("like/{postId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(
            summary = "Like to a post",
            description = "Allows a user to like a post"
    )
    public void sendLike(@PathVariable("postId") long postId) {
        postService.addLike(Utils.getCurrentUserId(), postId);
    }

    @PostMapping("disLike/{postId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(
            summary = "Dislike to a post",
            description = "Allows a user to dislike a post"
    )
    public void disLike(@PathVariable("postId") long postId) {
        postService.disLike(Utils.getCurrentUserId(), postId);
    }
}