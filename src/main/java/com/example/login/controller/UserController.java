package com.example.login.controller;

import com.example.login.model.request.NewPasswordRequest;
import com.example.login.model.response.UserResponse;
import com.example.login.security.CustomUserDetails;
import com.example.login.service.PasswordService;
import com.example.login.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;
    private final PasswordService passwordService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("me")
    public UserResponse findUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return service.getCurrentUser(userDetails.getId().intValue());
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping(value = "/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody String json) throws JsonProcessingException {
        service.patchUser(userId, json);
    }

    @GetMapping("/id/{id}")
    public UserResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    @PostMapping("/new-password")
    public ResponseEntity<Void> newPassword(@RequestBody @Valid NewPasswordRequest request) {
        passwordService.createNewPassword(request.getEmail());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestParam String otp, @RequestParam String newPassword) {
        passwordService.forgetPassword(otp, newPassword);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-merge-there-is-user-post-is-new")
    public ResponseEntity<Void> testCascadeMergeThereIsUserPostIsNew() {
        service.testCascadeMergeThereIsUserPostIsNew();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-persist-user-is-new-post-is-new")
    public ResponseEntity<Void> testCascadePersistUserIsNewPostIsNew() {
        service.testCascadePersistUserIsNewPostIsNew();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-all-without-orphan-removal")
    public ResponseEntity<Void> testCascadeAllWithoutOrphanRemoval() {
        service.testCascadeAllWithoutOrphanRemoval();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-all-without-orphan-removal-user-name-change")
    public ResponseEntity<Void> testCascadeAllWithoutOrphanRemovalUserNameChange() {
        service.testCascadeAllWithoutOrphanRemovalUserNameChange();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-all-with-orphan-removal-true")
    public ResponseEntity<Void> testCascadeAllWithOrphanRemovalTrue() {
        service.testCascadeAllWithOrphanRemovalTrue();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cascade-merge")
    public ResponseEntity<Void> testCascadeMerge() {
        service.testCascadeMerge();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-deleted-post-and-comment-deleted")
    public ResponseEntity<Void> testCascadeAllWithOrphanRemovalTrueUserDeletedPostAndCommentDeleted() {
        service.testCascadeAllWithOrphanRemovalTrueUserDeletedPostAndCommentDeleted();
        return ResponseEntity.noContent().build();
    }

}
