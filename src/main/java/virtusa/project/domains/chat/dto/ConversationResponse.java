package virtusa.project.domains.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Builder
public class ConversationResponse {


    /**
     * Chat conversation ID.
     */
    private UUID conversationId;


    /**
     * Property information.
     */
    private UUID propertyId;

    private String propertyTitle;

    private String propertyImageUrl;


    /**
     * The other person in the conversation.
     *
     * Buyer sees Agent details.
     * Agent sees Buyer details.
     *
     * Firebase UID.
     */
    private String participantId;

    private String participantName;

    private String participantImageUrl;


    /**
     * Preview of the latest message.
     */
    private String lastMessage;


    /**
     * Time of latest activity.
     */
    private LocalDateTime lastMessageTime;


    /**
     * Number of unread messages
     * for the current user.
     */
    private long unreadCount;

}