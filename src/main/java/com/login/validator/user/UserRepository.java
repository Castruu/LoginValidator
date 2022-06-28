package com.login.validator.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

@Repository
@ApplicationScope
public class UserRepository {

    @Getter @Setter
    private User loggedUser;

    private final Map<String, User> userMap = new HashMap<>();

    public boolean userAlreadyExists(String email) {
        return userMap.containsKey(email);
    }

    public User getUserByEmail(String email) {
        return userMap.get(email);
    }

    public void registerUser(User user) {
        userMap.put(user.getEmail(), user);
    }

}
