package com.example.login.service;

import com.example.login.config.bean.AllInternalPostClient;
import com.example.login.config.bean.JsonPlaceHolderClient;
import com.example.login.config.bean.SatelliteClient;
import com.example.login.model.response.PostResponse;
import com.example.login.model.response.PostResponseExt;
import com.example.login.model.response.TleResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebClientService {

    private final SatelliteClient satelliteClient;
    private final JsonPlaceHolderClient jsonPlaceHolderClient;
    private final AllInternalPostClient allInternalPostClient;

    @Retry(name = "getAllSatellites")
    @CircuitBreaker(
            name = "satelliteCircuitBreaker",
            fallbackMethod = "satelliteFallback"
    )
    @Bulkhead(
            name = "satelliteBulkhead",
            type = Bulkhead.Type.THREADPOOL,
            fallbackMethod = "satelliteFallback")
    public TleResponse getAllSatellites() {
        log.info("calling Satellite API");
        return satelliteClient.getSatellites();
    }

    @Retry(name = "getPostById")
    @CircuitBreaker(
            name = "postCircuitBreaker",
            fallbackMethod = "postFallback")
    @Bulkhead(
            name = "postBulkhead",
            type = Bulkhead.Type.THREADPOOL,
            fallbackMethod = "postFallback")
    public PostResponseExt getPost(Long id) {
        log.info("calling Json Place Holder,find post by id API");
        return jsonPlaceHolderClient.getPost(id);
    }

    @Retry(name = "getAllPosts")
    @CircuitBreaker(
            name = "getAllPostsCircuitBreaker",
            fallbackMethod = "allPostsFallback")
    public Flux<PostResponse> getAllPosts() {
        log.info("calling get All Internal Posts");
        return allInternalPostClient.getAllInternalPosts();
    }

    private TleResponse satelliteFallback(Throwable throwable) {
        log.error("Satellite API unavailable", throwable);

        TleResponse response = new TleResponse();
        response.setTotalItems(0L);
        return response;
    }

    private PostResponseExt postFallback(Long id, Exception exception) {
        log.error("Post API unavailable for id={}", id, exception);

        PostResponseExt response = new PostResponseExt();
        response.setId(id);
        response.setTitle("Default Post");
        response.setBody("External service unavailable");

        return response;
    }

    private Flux<PostResponse> allPostsFallback(Throwable ex) {
        log.error("getAllPosts fallback triggered due to: {}", ex.getMessage());

        log.error("getAllPosts fallback triggered: {}", ex.getMessage());

        return Flux.empty();
    }
}
