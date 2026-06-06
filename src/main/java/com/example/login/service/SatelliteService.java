package com.example.login.service;

import com.example.login.config.bean.SatelliteClient;
import com.example.login.model.response.TleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class SatelliteService {

    private final SatelliteClient satelliteClient;

    public TleResponse getSatellites() {

        log.info("Calling Satellite API");

        return satelliteClient.getSatellites();
    }
}
