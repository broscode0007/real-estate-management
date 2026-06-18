package virtusa.project.domains.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.dto.SendMessageRequest;
import virtusa.project.domains.chat.model.Conversation;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {


    private final ConversationService conversationService;


    private final MessageService messageService;


    /**
     * Create a conversation.
     */
    @Override
    public Conversation createConversation(
            String buyerId,
            String agentId,
            UUID propertyId
    ) {

        return conversationService.createConversation(
                buyerId,
                agentId,
                propertyId
        );
    }


    /**
     * Send a normal chat message.
     */
    @Override
    public MessageResponse sendMessage(
            String senderId,
            SendMessageRequest request
    ) {

        return messageService.sendMessage(
                senderId,
                request
        );
    }


    /**
     * Send a system generated message.
     */
    @Override
    public MessageResponse sendSystemMessage(
            UUID conversationId,
            String content
    ) {

        return messageService.sendSystemMessage(
                conversationId,
                content
        );
    }


    /**
     * Get chat history.
     * 
     * User must be validated as a participant.
     */
    @Override
    public Page<MessageResponse> getMessages(
            UUID conversationId,
            String userId,
            Pageable pageable
    ) {

        return messageService.getMessages(
                conversationId,
                userId,
                pageable
        );
    }


    /**
     * Get all conversations for a user.
     */
    @Override
    public List<ConversationResponse> getUserConversations(
            String userId
    ) {

        return conversationService.getUserConversations(
                userId
        );
    }


    /**
     * Mark conversation messages as read.
     */
    @Override
    public void markConversationAsRead(
            UUID conversationId,
            String userId
    ) {

        messageService.markConversationAsRead(
                conversationId,
                userId
        );
    }

}