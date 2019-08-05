package com.szura.htoneage.controller;

import com.szura.htoneage.dto.MessageDTO;
import com.szura.htoneage.service.MessageService;
import com.szura.htoneage.validator.MessageValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    private MessageValidator messageValidator;

    public MessageController(MessageService messageService, MessageValidator messageValidator) {
        this.messageService = messageService;
        this.messageValidator = messageValidator;
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postMessage(@RequestBody MessageDTO message) {
        messageValidator.validateMessage(message);
        messageService.postNewMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/wall/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> showWall(@PathVariable String username) {
        return messageService.getMessagesForUser(username);
    }

    @GetMapping(value = "/timeline/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> showTimeline(@PathVariable String username) {
        return messageService.getMessagesInTimeline(username);
    }

}
