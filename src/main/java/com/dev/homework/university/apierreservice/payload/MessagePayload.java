package com.dev.homework.university.apierreservice.payload;

import lombok.Data;

@Data
public class MessagePayload {
    private String senderUsername;
    private String recipientUsername;
    private String content;
}
