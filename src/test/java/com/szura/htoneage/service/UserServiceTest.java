package com.szura.htoneage.service;

import com.szura.htoneage.dto.FollowUserDto;
import com.szura.htoneage.exception.UserNotExistException;
import com.szura.htoneage.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UserNotExistException.class)
    public void test_followNotExistingUserExpectException() {
        FollowUserDto followUserDto = new FollowUserDto();
        followUserDto.setUsername("user");
        followUserDto.setUsernameToFollow("followedUser");

        Mockito.when(userRepository.findUserByName("user")).thenReturn(null);

        userService.followUser(followUserDto);

        Mockito.verify(userRepository, Mockito.never()).findUserByName("followedUser");
    }
}
