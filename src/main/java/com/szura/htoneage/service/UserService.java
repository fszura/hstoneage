package com.szura.htoneage.service;

import com.szura.htoneage.dto.FollowUserDto;
import com.szura.htoneage.entries.User;
import com.szura.htoneage.exception.UserNotExistException;
import com.szura.htoneage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(String username){
        User user = userRepository.findUserByName(username);
        return user!=null ? user : userRepository.createUser(username);
    }

    public User getUser(String username){
        return userRepository.findUserByName(username);
    }

    public void followUser(FollowUserDto followUser) {
        User user = Optional.ofNullable(userRepository.findUserByName(followUser.getUsername())).orElseThrow(UserNotExistException::new);
        User userToFollow = Optional.ofNullable(userRepository.findUserByName(followUser.getUsernameToFollow())).orElseThrow(UserNotExistException::new);

        if (user.getFollow() == null) {
            user.setFollow(new ArrayList<>());
        }
        user.getFollow().add(userToFollow);
    }
}
