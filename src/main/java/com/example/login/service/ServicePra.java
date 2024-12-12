package com.example.login.service;

import com.example.login.repository.CommentRepository;
import com.example.login.repository.PostRepository;
import com.example.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicePra {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void test() {
//        List<Comment> commentByPostId = commentRepository.findCommentByPostId(90L);
//        log.info("findCommentByPostId: {}", commentByPostId);


    }
}
