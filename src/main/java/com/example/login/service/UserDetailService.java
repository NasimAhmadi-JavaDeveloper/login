package com.example.login.service;

import com.example.login.model.entity.user.User;
import com.example.login.model.entity.user.UserDetail;
import com.example.login.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;

    public void unblockUser(int userId) {
        userDetailRepository.unblockUser(userId);
    }

    public void blockUser(int userId) {
        userDetailRepository.blockUser(userId);
    }

    public void incBanWordCount(int userId, int newBanWord) {
        userDetailRepository.incBanWordCount(userId, newBanWord);
    }

    public void createUserDetailWithBanWordCount(User user) {
        UserDetail userDetail = UserDetail.builder()
                .banWordCount(1)
                .user(user)
                .build();
        user.setUserDetail(userDetail);
        userDetailRepository.save(userDetail);
    }
}
