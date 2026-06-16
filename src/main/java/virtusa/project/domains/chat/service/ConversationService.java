package virtusa.project.domains.chat.service;

import java.util.List;
import java.util.UUID;

import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.model.Conversation;


public interface ConversationService {


    /**
     * Create a conversation between
     * a buyer, agent and property.
     *
     * Called automatically when a
     * reservation is created.
     */
    Conversation createConversation(
            String buyerId,
            String agentId,
            UUID propertyId
    );


    /**
     * Find a conversation by ID.
     *
     * Used by MessageService.
     */
    Conversation getConversation(
            UUID conversationId
    );


    /**
     * Get all conversations for a user.
     *
     * Returns a WhatsApp-style chat list.
     */
    List<ConversationResponse> getUserConversations(
            String userId
    );


    /**
     * Verify that a user belongs
     * to this conversation.
     *
     * Used before sending messages,
     * reading messages, etc.
     */
    void validateParticipant(
            Conversation conversation,
            String userId
    );

}