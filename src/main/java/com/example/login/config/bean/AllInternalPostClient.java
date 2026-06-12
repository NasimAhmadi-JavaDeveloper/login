package com.example.login.config.bean;

import com.example.login.exception.LogicalException;
import com.example.login.model.enums.ExceptionSpec;
import com.example.login.model.response.PostResponse;
import com.example.login.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllInternalPostClient {

    private final WebClient allInternalPost;

    public Flux<PostResponse> getAllInternalPosts() {

        return allInternalPost
                .get()
                .uri("/post")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + SecurityUtil.getCurrentToken())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse ->
                                Mono.error(new LogicalException(ExceptionSpec.CLIENT_ERROR))
                ).onStatus(HttpStatus::is5xxServerError,
                        clientResponse ->
                                Mono.error(new LogicalException(ExceptionSpec.SERVICE_UNAVAILABLE))
                )
                .bodyToFlux(PostResponse.class);
    }
}
