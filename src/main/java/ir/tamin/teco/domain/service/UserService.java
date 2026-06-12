package ir.tamin.teco.domain.service;

import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.model.User;

public interface UserService {

    void validateAge(long userId);
    void createUser(User user);
}
