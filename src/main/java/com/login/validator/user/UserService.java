package com.login.validator.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void setLoggedUser(User user) {
        repository.setLoggedUser(user);
    }

    public User getLoggedUser() {
        return repository.getLoggedUser();
    }

    public boolean isUserLogged() {
        return getLoggedUser() != null;
    }

    public boolean registerUserIfNotExists(User user) {
        if(repository.userAlreadyExists(user.getEmail())) {
            return false;
        }
        repository.registerUser(user);
        return true;
    }

    public User loginUserIfExists(UserLogin user) {
        if(!repository.userAlreadyExists(user.getEmail())) {
            return null;
        }
        User savedUser = repository.getUserByEmail(user.getEmail());
        if(!verifyUserPassword(user.getPassword(), savedUser.getPassword())) {
            return null;
        }
        return savedUser;
    }

    private boolean verifyUserPassword(String password, String savedUserPassword) {
        return savedUserPassword.equals(password);
    }



}
