package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.PostMapper;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.request.PostRequest;
import com.example.login.model.response.PostResponse;
import com.example.login.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public PostResponse createPost(PostRequest.PostCreateDto dto) {
        User user = userService.getUser(dto.getUserId());

        Post post = postMapper.toEntity(dto);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
        return postMapper.toDto(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
        postRepository.delete(post);
    }
}