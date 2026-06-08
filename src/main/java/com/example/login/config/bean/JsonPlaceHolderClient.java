package com.example.login.config.bean;

import com.example.login.model.response.PostResponseExt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonPlaceHolderClient {

    private final WebClient jsonPlaceHolderWebClient;

    public PostResponseExt getPost(Long id) {
        log.info("Json Place Holder API");
        return jsonPlaceHolderWebClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostResponseExt.class)
                .block();
    }
}
