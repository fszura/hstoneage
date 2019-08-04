package com.szura.htoneage.service;

import com.szura.htoneage.dto.MessageDTO;
import com.szura.htoneage.entries.Message;
import com.szura.htoneage.entries.User;
import com.szura.htoneage.mapper.MessageMapper;
import com.szura.htoneage.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_postMessageAndCreateUser() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUsername("user");
        messageDTO.setMessage("content");

        User user = Mockito.mock(User.class);

        Mockito.when(userService.getOrCreateUser(messageDTO.getUsername())).thenReturn(user);

        messageService.postNewMessage(messageDTO);

        Mockito.verify(user, Mockito.times(2)).getMessages();
    }

    @Test
    public void test_getWallOfMessagesForExistingUser() {
        String username = "username";
        Message msg = new Message("abc", new Date());
        List<Message> messageList = new ArrayList<>();
        messageList.add(msg);

        Mockito.when(messageRepository.findMessagesForUser(username)).thenReturn(messageList);
        List<MessageDTO> messageDtosList = new ArrayList<>();
        MessageDTO messageDTO = new MessageDTO(msg.getContent(), msg.getTime());
        messageDtosList.add(messageDTO);
        Mockito.when(messageMapper.mapToDtos(messageList)).thenReturn(messageDtosList);

        List<MessageDTO> result = messageService.getMessagesForUser(username);

        Assert.notNull(result);
        Assert.isTrue(result.get(0).getUsername().equals(username));

    }

    @Test
    public void test_getTimelineOfUsersWhichAreFollowed() {
        String username = "user1";
        User user = new User();
        user.setUsername(username);
        User followed = new User();

        List<Message> messageList = new ArrayList<>();
        Message message = new Message();
        messageList.add(message);
        followed.setMessages(messageList);
        List<User> followedUser = new ArrayList<>();
        followedUser.add(followed);
        user.setFollow(followedUser);

        Mockito.when(messageRepository.findMessagesForUser(Mockito.anyString())).thenReturn(messageList);
        List<MessageDTO> messageDtosList = new ArrayList<>();
        MessageDTO messageDTO = new MessageDTO(message.getContent(), message.getTime());
        messageDtosList.add(messageDTO);
        Mockito.when(messageMapper.mapToDtos(messageList)).thenReturn(messageDtosList);
        Mockito.when(userService.getUser(username)).thenReturn(user);
        List<MessageDTO> result = messageService.getMessagesInTimeline(username);

        Assert.notEmpty(result);
    }
}
