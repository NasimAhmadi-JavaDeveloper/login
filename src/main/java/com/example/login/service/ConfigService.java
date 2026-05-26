package com.example.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    @Value("${ban.word.threshold}")
    private int banWordThreshold;

    public int getBandWordCount() {
        return banWordThreshold;
    }
}
