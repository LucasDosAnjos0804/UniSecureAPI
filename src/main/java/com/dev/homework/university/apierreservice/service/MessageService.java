package com.dev.homework.university.apierreservice.service;

import com.dev.homework.university.apierreservice.entity.Message;
import com.dev.homework.university.apierreservice.entity.User;
import com.dev.homework.university.apierreservice.repository.MessageRepository;
import com.dev.homework.university.apierreservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(String senderUsername, String recipientUsername, String content) {
        Optional<User> sender = userRepository.findByUsername(senderUsername);
        Optional<User> recipient = userRepository.findByUsername(recipientUsername);

        if (sender.isEmpty() || recipient.isEmpty()) {
            throw new RuntimeException("Sender or recipient not found");
        }

        Message message = new Message();
        message.setSender(sender.get());
        message.setRecipient(recipient.get());
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessagesForUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        List<Message> messages = messageRepository.findByRecipientOrderByTimestampDesc(user.get());
        return messages;
    }
}
