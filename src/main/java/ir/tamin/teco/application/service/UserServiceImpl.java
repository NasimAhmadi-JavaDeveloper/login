package ir.tamin.teco.application.service;

import ir.tamin.teco.domain.model.User;
import ir.tamin.teco.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void validateAge(long userId) {

    }

    @Override
    public void create(User user) {
        //map to entity -> save via userRepo -> jpaRepo
    }
}
