package com.szura.htoneage.entries;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Message {

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, Date time) {
        this.content = content;
        this.time = time;
    }

    private String content;

    private Date time;
}
