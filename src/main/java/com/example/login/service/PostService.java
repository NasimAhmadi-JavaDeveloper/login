package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.PostMapper;
import com.example.login.model.dto.PostStatsDto;
import com.example.login.model.entity.Comment;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.proj.LikeProjection;
import com.example.login.model.request.PostRequest;
import com.example.login.model.response.PostResponse;
import com.example.login.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final NotificationService notificationService;

    public PostResponse createPost(PostRequest.PostCreateDto dto) {
        User user = userService.getUser(dto.getUserId());

        Post post = postMapper.toEntity(dto);
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        //send email as asynchronous
        notificationService.sendNotificationEmail(user.getEmail(), "Your post has been created successfully!");

        return postMapper.toDto(savedPost);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
        return postMapper.toDto(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
        postRepository.delete(post);
    }

    public void addLike(int userId, long postId) {
        final User user = userService.getUser(userId);
        final Post post = getPost(postId);

        if (Objects.isNull(post.getLikes())) {
            post.setLikes(Collections.singleton(user));
        } else {
            post.getLikes().add(user);
        }
        postRepository.save(post);
    }

    public Post getPost(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.POST_NOT_FOUND));
    }

    public void disLike(int userId, long postId) {


    }

    public void testCascadePersist() {
        Post post = new Post()
                .setCaption("test11")
                .setVisitCount(0)
                .setImageUrls(Arrays.asList("https://test2", "https://test3"))
                .setUser(userService.getUser(2))
                .setTag(Arrays.asList("#spring11 boot111", "#List11", "#Set11"));

        Comment comment1 = new Comment()
                .setCommentText("Great post!")
                .setPost(post)
                .setUser(userService.getUser(2));

        Comment comment2 = new Comment()
                .setCommentText("Nice picture!")
                .setPost(post)
                .setUser(userService.getUser(2));

        post.setComments(Arrays.asList(comment1, comment2));
        postRepository.save(post);
    }

    public List<PostStatsDto> getPostStatsByHour() {
        return postRepository.countPostsByHour();
    }


    public List<PostResponse> getUserPosts(int userId) {
        return postRepository.findUserPosts(userId)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public long countDaysUserLikedNewUsers(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        return postRepository.countDistinctLikedDatesByUserAndDateRange(userId, startDate, endDate);
    }

    public long countDaysWithNewLikes(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        // Fetch all likes data for the given user and date range
        List<LikeProjection> likeData = postRepository.findLikeDataByUserIdAndDateRange(userId, startDate, endDate);

        Map<LocalDate, List<User>> groupedLikes = likeData.stream()
                .collect(Collectors.groupingBy(
                        LikeProjection::getLikeDate,
                        Collectors.mapping(LikeProjection::getUser, Collectors.toList())
                ));

        Set<Integer> seenUsers = new HashSet<>();
        long count = 0;

        List<LocalDate> likesDate = groupedLikes
                .keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());

        for (LocalDate date : likesDate) {
            List<User> likedUsers = groupedLikes.get(date);
            boolean hasNewUser = false;

            for (User user : likedUsers) {
                if (seenUsers.add(user.getId())) {
                    hasNewUser = true;
                }
            }
            if (hasNewUser) {
                count++;
            }
        }

        return count;
    }

}