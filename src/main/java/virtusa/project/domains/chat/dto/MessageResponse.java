package virtusa.project.domains.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

import virtusa.project.domains.chat.model.MessageStatus;
import virtusa.project.domains.chat.model.MessageType;
import virtusa.project.domains.chat.model.SenderType;


@Getter
@Builder
public class MessageResponse {


    /**
     * Message ID
     */
    private UUID id;


    /**
     * Conversation this message belongs to
     */
    private UUID conversationId;


    /**
     * Sender user ID
     */
    private String senderId;


    /**
     * BUYER or AGENT
     */
    private SenderType senderType;


    /**
     * Message body
     */
    private String content;


    /**
     * TEXT, IMAGE, FILE
     */
    private MessageType messageType;


    /**
     * SENT, DELIVERED, READ
     */
    private MessageStatus status;


    /**
     * Time the message was created
     */
    private LocalDateTime createdAt;

}