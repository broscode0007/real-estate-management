package com.realestate.marketplace.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                chatMessage
        );
    }

    @RestController
    @RequestMapping("/api/chat")
    public static class ChatRestController {
        
        @GetMapping("/history")
        public List<ChatMessage> getChatHistory(String user1, String user2) {
            List<ChatMessage> history = new ArrayList<>();
            history.add(new ChatMessage("1", user1, user2, "Hello!", LocalDateTime.now().minusMinutes(5)));
            history.add(new ChatMessage("2", user2, user1, "Hi there! How can I help you?", LocalDateTime.now().minusMinutes(4)));
            return history;
        }
    }

    public static class ChatMessage {
        private String id;
        private String senderId;
        private String recipientId;
        private String content;
        private LocalDateTime timestamp;

        public ChatMessage() {}

        public ChatMessage(String id, String senderId, String recipientId, String content, LocalDateTime timestamp) {
            this.id = id;
            this.senderId = senderId;
            this.recipientId = recipientId;
            this.content = content;
            this.timestamp = timestamp;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getRecipientId() {
            return recipientId;
        }

        public void setRecipientId(String recipientId) {
            this.recipientId = recipientId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}

