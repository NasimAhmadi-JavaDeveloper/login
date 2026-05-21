package com.example.login.controller;

import com.example.login.model.request.ForbiddenWordRequest;
import com.example.login.service.ForbiddenWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forbidden-word")
public class ForbiddenWordController {

    private final ForbiddenWordService forbiddenWordService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void createForbiddenWord(@Valid @RequestBody ForbiddenWordRequest request) {
        forbiddenWordService.addForbiddenWord(request);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteForbiddenWord(Integer id) {
        forbiddenWordService.deleteForbiddenWord(id);
    }
}
