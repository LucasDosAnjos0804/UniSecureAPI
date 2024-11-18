package com.dev.homework.university.apierreservice.controller;

import com.dev.homework.university.apierreservice.dto.MessageDTO;
import com.dev.homework.university.apierreservice.payload.MessagePayload;
import com.dev.homework.university.apierreservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessagePayload payload) {
        log.info("User {} trying to send message to {}", payload.getSenderUsername(), payload.getRecipientUsername());
        try {
            messageService.sendMessage(payload.getSenderUsername(), payload.getRecipientUsername(), payload.getContent());
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            log.error("Failed to send message", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send message: " + e.getMessage());
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable String username) {
        log.info("Fetching messages for user: {}", username);
        try {
            List<MessageDTO> messages = messageService.getMessagesForUser(username).stream().map(MessageDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            log.error("Failed to fetch messages for user: {}", username, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
