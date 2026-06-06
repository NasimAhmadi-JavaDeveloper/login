package com.example.login.config.bean;

import com.example.login.exception.LogicalException;
import com.example.login.model.enums.ExceptionSpec;
import com.example.login.model.response.TleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SatelliteClient {

    private final WebClient satelliteWebClient;

    public TleResponse getSatellites() {
        log.info("Calling NASA API");
        return satelliteWebClient
                .get()
                .uri("/api/tle")
                .retrieve() //dont use direct
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse ->
                                Mono.error(new LogicalException(ExceptionSpec.CLIENT_ERROR)))
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse ->
                                Mono.error(new LogicalException(ExceptionSpec.SERVICE_UNAVAILABLE)))
                .bodyToMono(TleResponse.class)
                .block();
    }
}
