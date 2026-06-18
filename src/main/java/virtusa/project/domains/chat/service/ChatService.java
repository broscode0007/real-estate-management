package virtusa.project.domains.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.dto.SendMessageRequest;
import virtusa.project.domains.chat.model.Conversation;


public interface ChatService {


    /**
     * Create a conversation.
     *
     * Called automatically after a reservation
     * is created.
     */
    Conversation createConversation(
            String buyerId,
            String agentId,
            UUID propertyId
    );


    /**
     * Send a normal chat message.
     */
    MessageResponse sendMessage(
            String senderId,
            SendMessageRequest request
    );


    /**
     * Send a system generated message.
     */
    MessageResponse sendSystemMessage(
            UUID conversationId,
            String content
    );


    /**
     * Get messages of a conversation.
     *
     * The user must be a participant
     * in the conversation.
     */
    Page<MessageResponse> getMessages(
            UUID conversationId,
            String userId,
            Pageable pageable
    );


    /**
     * Get all conversations of a user.
     */
    List<ConversationResponse> getUserConversations(
            String userId
    );


    /**
     * Mark messages as read.
     */
    void markConversationAsRead(
            UUID conversationId,
            String userId
    );

}