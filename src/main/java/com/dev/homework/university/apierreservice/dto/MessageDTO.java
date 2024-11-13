package com.dev.homework.university.apierreservice.dto;

import com.dev.homework.university.apierreservice.entity.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long id;
    private String content;
    private String senderUsername;
    private String receiverUsername;
    private LocalDateTime timestamp;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.senderUsername = message.getSender().getUsername();
        this.receiverUsername = message.getRecipient().getUsername();
        this.timestamp = message.getTimestamp();
    }
}
