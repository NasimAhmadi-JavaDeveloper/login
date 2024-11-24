package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.PostMapper;
import com.example.login.model.entity.Comment;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.request.PostRequest;
import com.example.login.model.response.PostResponse;
import com.example.login.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public void addLike(int userId, long postId) {
        final User user = userService.getUser(userId);
        final Post post = getPost(postId);

        if (Objects.isNull(post.getLikes())) {
            post.setLikes(Collections.singleton(user));
        } else {
            post.getLikes().add(user);
        }
        postRepository.save(post);
    }

    public Post getPost(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
    }

    public void disLike(int userId, long postId) {


    }

    public void testCascadePersist() {
        Post post = new Post()
                .setCaption("test11")
                .setVisitCount(0)
                .setImageUrls(Arrays.asList("https://test2","https://test3"))
                .setUser(userService.getUser(2))
                .setTag(Arrays.asList("#spring11 boot111", "#List11", "#Set11"));

        Comment comment1 = new Comment()
                .setCommentText("Great post!")
                .setPost(post)
                .setUser(userService.getUser(2));

        Comment comment2 = new Comment()
                .setCommentText("Nice picture!")
                .setPost(post)
                .setUser(userService.getUser(2));

        post.setComments(Arrays.asList(comment1,comment2));
        postRepository.save(post);
    }
}
