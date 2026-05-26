package com.example.login.service;

import com.example.login.enums.Emoji;
import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.CommentMapper;
import com.example.login.model.entity.Comment;
import com.example.login.model.entity.Post;
import com.example.login.model.entity.User;
import com.example.login.model.entity.UserDetail;
import com.example.login.model.response.CommentResponse;
import com.example.login.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ConfigService configService;
    private final UserService userService;
    private final PostService postService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final ForbiddenWordService forbiddenWordService;
    private final UserDetailService userDetailService;

    public Page<CommentResponse> getComments(long postId, int page, int size) {
        return commentRepository.findAllComment(postId, PageRequest.of(page, size))
                .map(commentMapper::toCommentResponse);
    }

    public void sendComment(int userId, long postId, String comment, Emoji emoji) {
        User user = userService.getUser(userId);

        if (containsBanWord(comment)) {
            handleBanWordViolation(user);
        } else {
            Post post = postService.getPost(postId);
            createComment(comment, emoji, post, user);
        }
    }

    private boolean containsBanWord(String comment) {
        return forbiddenWordService.containsBanWord(comment);
    }

    private void handleBanWordViolation(User user) {

        int banWordCount;

        UserDetail userDetail = user.getUserDetail();

        if (Objects.nonNull(userDetail) && Objects.nonNull(userDetail.getBanWordCount())) {

            banWordCount = user.getUserDetail().getBanWordCount();

            int newBanWord = banWordCount + 1;

            if (newBanWord >= configService.getBandWordCount()) {

                userDetailService.blockUser(user.getId());

                throw new LogicalException(ExceptionSpec.USER_ALREADY_BLOCKED);
            }

            userDetailService.incBanWordCount(user.getId(), newBanWord);

            throw new LogicalException(ExceptionSpec.BAN_WORD_DETECTED);
        }

        userDetailService.createUserDetailWithBanWordCount(user);

        throw new LogicalException(ExceptionSpec.BAN_WORD_DETECTED);
    }

    private void createComment(String comment, Emoji emoji, Post post, User user) {
        commentRepository.save(new Comment()
                .setCommentText(comment)
                .setEmoji(emoji)
                .setPost(post)
                .setUser(user));
    }

    public void removeComment(int userId, long commentId) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(userId)) {
            throw new LogicalException(ExceptionSpec.NOT_YOUR_COMMENT);
        }
        commentRepository.delete(comment);
    }

}
