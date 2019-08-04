package com.szura.htoneage.service;

import com.szura.htoneage.dto.MessageDTO;
import com.szura.htoneage.entries.Message;
import com.szura.htoneage.entries.User;
import com.szura.htoneage.mapper.MessageMapper;
import com.szura.htoneage.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {

    private UserService userService;

    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    public MessageService(UserService userService, MessageRepository messageRepository, MessageMapper messageMapper) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public void postNewMessage(MessageDTO messageDTO) {
        User user = userService.getOrCreateUser(messageDTO.getUsername());
        log.debug("Get user for username: {}", messageDTO.getUsername());
        Message message = new Message();
        message.setContent(messageDTO.getMessage());
        message.setTime(new Date());
        if (user.getMessages()==null) {
            user.setMessages(new ArrayList<>());
        }
        log.debug("Messages which will be added: {}", message);
        user.getMessages().add(message);

        log.info("Message add");
    }

    public List<MessageDTO> getMessagesForUser(String username) {
        List<Message> messages = messageRepository.findMessagesForUser(username);
        List<MessageDTO> messageDTOS = messageMapper.mapToDtos(messages);
        messageDTOS.forEach(m->m.setUsername(username));
        return messageDTOS;
    }

    public List<MessageDTO> getMessagesInTimeline(String username) {
        User user = userService.getUser(username);
        if (user == null || user.getFollow() == null) {
            return new ArrayList<>();
        }

        List<List<MessageDTO>> collect = user.getFollow().stream().map(u -> {
            List<MessageDTO> messagesForUser = getMessagesForUser(u.getUsername());
            messagesForUser.forEach(m -> m.setUsername(u.getUsername()));
            return messagesForUser;
        }).collect(Collectors.toList());
        List<MessageDTO> result = new ArrayList<>();
        collect.forEach(result::addAll);
        result.sort(Comparator.comparing(MessageDTO::getTime));
        return result;
    }
}
