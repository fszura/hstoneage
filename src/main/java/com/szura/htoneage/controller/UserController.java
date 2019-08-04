package com.szura.htoneage.controller;

import com.szura.htoneage.dto.FollowUserDto;
import com.szura.htoneage.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean followUser(@RequestBody FollowUserDto followUser) {
        userService.followUser(followUser);
        return true;
    }
}
