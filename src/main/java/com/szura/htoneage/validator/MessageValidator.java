package com.szura.htoneage.validator;

import com.szura.htoneage.dto.MessageDTO;
import com.szura.htoneage.exception.MessageValidationException;
import org.springframework.stereotype.Component;

@Component
public class MessageValidator {

    public void validateMessage(MessageDTO messageDTO) {
        if (messageDTO.getMessage() == null
                || messageDTO.getMessage().isEmpty() ||
                messageDTO.getMessage().length()>0) {
            throw new MessageValidationException();
        }
    }
}
