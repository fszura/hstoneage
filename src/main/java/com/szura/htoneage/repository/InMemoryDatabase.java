package com.szura.htoneage.repository;

import com.szura.htoneage.entries.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryDatabase {

    private Map<String, User> users;

    public InMemoryDatabase() {
        this.users = new HashMap<>();
    }

    User createUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            User user = new User();
            user.setUsername(username);
            users.put(username, user);
            return user;
        }
    }

    User getUser(String username) {
        return this.users.get(username);
    }
}
