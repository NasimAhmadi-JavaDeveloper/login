package com.example.login.controller;

import com.example.login.model.response.PostResponse;
import com.example.login.model.response.PostResponseExt;
import com.example.login.model.response.TleResponse;
import com.example.login.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/remote-api")
public class WebClientController {

    private final WebClientService webClientService;

    @GetMapping("/json-place-holder/post/{id}")
    public PostResponseExt getPost(@PathVariable Long id) {
        return webClientService.getPost(id);
    }

    @GetMapping("/tle")
    public TleResponse getSatellites() {
        return webClientService.getAllSatellites();
    }

    @GetMapping("/internal/posts")
    public List<PostResponse> getAllPosts() {
        return webClientService.getAllPosts();
    }

}