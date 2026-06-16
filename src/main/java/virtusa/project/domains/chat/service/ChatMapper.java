package virtusa.project.domains.chat.service;

import org.springframework.stereotype.Component;

import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.model.Conversation;
import virtusa.project.domains.chat.model.Message;


@Component
public class ChatMapper {


    /**
     * Convert Message entity into DTO.
     */
    public MessageResponse toMessageResponse(
            Message message
    ) {

        return MessageResponse.builder()
                .id(
                        message.getId()
                )
                .conversationId(
                        message.getConversation()
                                .getId()
                )
                .senderId(
                        message.getSenderId()
                )
                .senderType(
                        message.getSenderType()
                )
                .content(
                        message.getContent()
                )
                .messageType(
                        message.getMessageType()
                )
                .status(
                        message.getStatus()
                )
                .createdAt(
                        message.getCreatedAt()
                )
                .build();
    }


    /**
     * Convert Conversation into a chat list item.
     *
     * The service provides:
     * - Who the other participant is
     * - Participant display information
     * - Property thumbnail
     * - Latest message
     * - Unread count
     */
    public ConversationResponse toConversationResponse(
            Conversation conversation,
            String participantId,
            String participantName,
            String participantImageUrl,
            String propertyImageUrl,
            String lastMessage,
            long unreadCount
    ) {

        return ConversationResponse.builder()
                .conversationId(
                        conversation.getId()
                )
                .propertyId(
                        conversation.getProperty()
                                .getId()
                )
                .propertyTitle(
                        conversation.getProperty()
                                .getTitle()
                )
                .propertyImageUrl(
                        propertyImageUrl
                )
                .participantId(
                        participantId
                )
                .participantName(
                        participantName
                )
                .participantImageUrl(
                        participantImageUrl
                )
                .lastMessage(
                        lastMessage
                )
                .lastMessageTime(
                        conversation.getUpdatedAt()
                )
                .unreadCount(
                        unreadCount
                )
                .build();
    }

}