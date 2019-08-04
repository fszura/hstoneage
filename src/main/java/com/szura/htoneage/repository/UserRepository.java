package com.szura.htoneage.repository;

import com.szura.htoneage.entries.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private InMemoryDatabase database;

    public UserRepository(InMemoryDatabase database) {
        this.database = database;
    }

    public User findUserByName(String username) {
        return database.getUser(username);
    }

    public User createUser(String username) {
        return database.createUser(username);
    }
}
