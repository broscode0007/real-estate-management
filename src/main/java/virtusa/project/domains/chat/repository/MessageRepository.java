package virtusa.project.domains.chat.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import virtusa.project.domains.chat.model.Conversation;
import virtusa.project.domains.chat.model.Message;
import virtusa.project.domains.chat.model.MessageStatus;
import virtusa.project.domains.chat.model.SenderType;


public interface MessageRepository extends JpaRepository<Message, UUID> {


    /**
     * Load messages of a conversation.
     * Use pagination for large histories.
     */
    Page<Message> findByConversationOrderByCreatedAtDesc(
            Conversation conversation,
            Pageable pageable
    );


    /**
     * Get the latest message in a conversation.
     */
    Optional<Message> findTopByConversationOrderByCreatedAtDesc(
            Conversation conversation
    );


    /**
     * Count unread messages.
     *
     * Example:
     * Buyer wants to know how many messages
     * from the agent are still unread.
     */
    long countByConversationAndStatusAndSenderType(
            Conversation conversation,
            MessageStatus status,
            SenderType senderType
    );


    /**
     * Find all unread messages from a sender type.
     * Useful when opening a chat and marking them as read.
     */
    List<Message> findByConversationAndStatusAndSenderType(
            Conversation conversation,
            MessageStatus status,
            SenderType senderType
    );

}