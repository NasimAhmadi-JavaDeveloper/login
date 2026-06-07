package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient nasaWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.nasa.gov")
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