package com.szura.htoneage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {

    public MessageDTO(String message, Date time) {
        this.message = message;
        this.time = time;
    }

    private String username;
    private String message;
    private Date time;
}
