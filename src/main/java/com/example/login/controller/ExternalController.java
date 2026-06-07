package com.example.login.controller;

import com.example.login.model.response.PostResponseExt;
import com.example.login.model.response.TleResponse;
import com.example.login.service.ExternalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/external")
public class ExternalController {

    private final ExternalServices externalServices;

    @GetMapping("/json-place-holder/post/{id}")
    public PostResponseExt getPost(@PathVariable Long id) {
        return externalServices.getPost(id);
    }

    @GetMapping("/tle")
    public TleResponse getSatellites() {
        return externalServices.getAllSatellites();
    }

}