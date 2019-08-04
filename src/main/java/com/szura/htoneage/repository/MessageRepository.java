package com.szura.htoneage.repository;

import com.szura.htoneage.entries.Message;
import com.szura.htoneage.exception.UserNotExistException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {

    private InMemoryDatabase database;

    public MessageRepository(InMemoryDatabase database) {
        this.database = database;
    }

    public List<Message> findMessagesForUser(String username) {
        if (database.getUser(username) != null) {
            return database.getUser(username).getMessages();
        } else {
            throw new UserNotExistException();
        }
    }
}
