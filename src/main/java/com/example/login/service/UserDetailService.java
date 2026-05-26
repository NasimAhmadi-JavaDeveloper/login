package com.example.login.service;

import com.example.login.model.entity.User;
import com.example.login.model.entity.UserDetail;
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
        UserDetail userDetail = new UserDetail().setBanWordCount(1);
        user.setUserDetail(userDetail);
        userDetailRepository.save(userDetail);
    }
}
