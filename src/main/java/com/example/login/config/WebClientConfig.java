package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient allInternalPost() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api/v1")
                .build();
    }

    @Bean
    public WebClient satelliteWebClient() {
        return WebClient.builder()
                .baseUrl("https://tle.ivanstanojevic.me")
                .build();
    }

    @Bean
    public WebClient jsonPlaceHolderWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}