package com.szura.htoneage.entries;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String username;

    private List<Message> messages;

    private List<User> follow;
}
