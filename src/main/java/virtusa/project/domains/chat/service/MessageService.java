package virtusa.project.domains.chat.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.dto.SendMessageRequest;


public interface MessageService {


    /**
     * Send a message from a buyer or agent.
     *
     * senderId is taken from Firebase
     * authentication, not the frontend.
     */
    MessageResponse sendMessage(
            String senderId,
            SendMessageRequest request
    );


    /**
     * Send an automatic system message.
     *
     * Used for:
     * - Reservation created
     * - Reservation approved
     * - Reservation rejected
     * - Appointment scheduled
     */
    MessageResponse sendSystemMessage(
            UUID conversationId,
            String content
    );


    /**
     * Load messages of a conversation.
     *
     * The authenticated user must be a
     * participant in the conversation.
     *
     * Uses pagination because chats can
     * contain thousands of messages.
     */
    Page<MessageResponse> getMessages(
            UUID conversationId,
            String userId,
            Pageable pageable
    );


    /**
     * Mark messages from the other participant
     * as read.
     *
     * Example:
     *
     * Buyer opens chat:
     * Agent messages become READ.
     */
    void markConversationAsRead(
            UUID conversationId,
            String userId
    );

}