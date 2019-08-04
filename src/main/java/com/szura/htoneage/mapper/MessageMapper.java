package com.szura.htoneage.mapper;

import com.szura.htoneage.dto.MessageDTO;
import com.szura.htoneage.entries.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements EntryToDtoMapper<Message, MessageDTO> {
    @Override
    public Message mapToEntry(MessageDTO messageDTO) {
        return new Message(messageDTO.getMessage());
    }

    @Override
    public MessageDTO mapToDTO(Message entry) {
        return new MessageDTO(entry.getContent(), entry.getTime());
    }
}
