package com.example.login.service;

import com.example.login.enumeration.Role;
import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.UserMapper;
import com.example.login.model.entity.Comment;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.request.PatchUserRequest;
import com.example.login.model.request.UserRequest;
import com.example.login.model.response.UserResponse;
import com.example.login.repository.PostRepository;
import com.example.login.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SaveUserService saveUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    @Value("${security.lock-time-duration-seconds:1800}")
    public long lockTimeDurationSeconds;
    private static final int ZERO = 0;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    public void addUser(UserRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresent(u -> {
                    throw new LogicalException(ExceptionSpec.CONSTRAINT);
                });

        if (Objects.isNull(request.getId())) {
            saveUserService.saveUser(request);
        } else {
            updateUserService.updateUser(request);
        }
    }

    public void deleteUser(Integer id) {
        deleteUserService.deleteUser(id);
    }

    @Cacheable(value = "userById", key = "#id", unless = "#result == null")
    public UserResponse findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    @Cacheable(value = "userByEmail", key = "#email", unless = "#result == null")
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public UserResponse getCurrentUser(Integer id) {
        return userMapper.toResponse(getUser(id));
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public boolean isUserLocked(User user) {
        if (user.getLockTimeDuration() == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(user.getLockTimeDuration())) {
            user.setFailedLoginAttempts(ZERO);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }

    public void patchUser(Integer id, String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
        mapper.readValue(json, PatchUserRequest.class);
        mapper.readerForUpdating(user).readValue(json);
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.USER_NOT_FOUND));
    }


    public void updatePassword(String email, String newPassword) {
        User user = findUserByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void testCascadeMergeThereIsUserPostIsNew() {
        User user = getUserByName("test222222");
        Post post = new Post()
                .setCaption("test44")
                .setVisitCount(0)
                .setImageUrls(Arrays.asList("https://test44", "https://test44"))
                .setUser(user)
                .setTag(Arrays.asList("#spring44 boot444", "#List444", "#Set44"));

        user.getPosts().add(post); //post saved
        userRepository.save(user);
    }

    public void testCascadePersistUserIsNewPostIsNew() {
        User user = new User()
                .setUserName("namenew1111")
                .setPhone("95779543107")
                .setPassword(passwordEncoder.encode("name1123456789"))
                .setRole(Role.ROLE_USER);

        Post post = new Post()
                .setCaption("test11111111")
                .setVisitCount(0)
                .setImageUrls(Arrays.asList("https://test11111", "https://test1111111"))
                .setUser(user)
                .setTag(Arrays.asList("#spring44 boot444", "#List444", "#Set44"));

        user.getPosts().add(post);
        userRepository.save(user); //user saved and post saved too
    }

    public void testCascadeAllWithoutOrphanRemoval() { //OrphanRemoval = false
        User user = getUserByName("namenew1111");
        user.getPosts().remove(0); //this post does not deleted in post table
        userRepository.save(user);
    }

    public void testCascadeAllWithoutOrphanRemovalUserNameChange() { //OrphanRemoval = false
        User user = getUserByName("namenew1111");
        user.setUserName("test2121");//name changes
        user.getPosts().remove(0);//this post does not deleted in post table
        userRepository.save(user);
    }

    public void testCascadeAllWithOrphanRemovalTrue() {
        User user = getUserByName("test2121");
        user.getPosts().remove(0); // this post deleted in post table
        userRepository.save(user);
    }

    public void testCascadeMerge() {
        User user = getUserByName("test2121");
        Post post = user.getPosts().get(0);

        user.setUserName("setUser");
        post.setCaption("setCaption");
        userRepository.save(user);
    }

    public void testCascadeAllWithOrphanRemovalTrueUserDeletedPostAndCommentDeleted() {
        User user = new User()
                .setUserName("john_doe")
                .setPhone("95779543000")
                .setPassword(passwordEncoder.encode("pass123456789"))
                .setEmail("john@example.com");

        Post post = new Post()
                .setCaption("Hello World!")
                .setUser(user);

        Comment comment = new Comment()
                .setCommentText("Great post!")
                .setUser(user)
                .setPost(post);

        user.setPosts(Collections.singletonList(post));
        user.setComments(Collections.singletonList(comment));

        userRepository.save(user);
        userRepository.delete(user);
    }
}