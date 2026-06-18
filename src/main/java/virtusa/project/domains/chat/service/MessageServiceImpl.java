package virtusa.project.domains.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.chat.dispatcher.MessageDispatcher;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.dto.SendMessageRequest;
import virtusa.project.domains.chat.model.Conversation;
import virtusa.project.domains.chat.model.Message;
import virtusa.project.domains.chat.model.MessageStatus;
import virtusa.project.domains.chat.model.MessageType;
import virtusa.project.domains.chat.model.SenderType;
import virtusa.project.domains.chat.repository.MessageRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {


    /**
     * Used for automatic messages generated
     * by the platform.
     */
    private static final String SYSTEM_USER = "SYSTEM";


    private final MessageRepository messageRepository;

    private final MessageDispatcher messageDispatcher;
        
    private final ConversationService conversationService;


    private final ChatMapper chatMapper;

        /**
     * Send a message from a buyer or agent.
     *
     * Sender ID comes from Firebase authentication,
     * never from the frontend request.
     */
    @Override
    public MessageResponse sendMessage(
            String senderId,
            SendMessageRequest request
    ) {

        /*
         * Load the conversation.
         */
        Conversation conversation =
                conversationService.getConversation(
                        request.getConversationId()
                );


        /*
         * Ensure the sender belongs
         * to this conversation.
         */
        conversationService.validateParticipant(
                conversation,
                senderId
        );


        /*
         * Determine sender type.
         */
        SenderType senderType;


        if (conversation.getBuyer()
                .getFirebaseUid()
                .equals(senderId)) {

            senderType = SenderType.BUYER;

        } else {

            senderType = SenderType.AGENT;
        }


        /*
         * Create the message.
         */
        Message message = Message.builder()
                .conversation(conversation)
                .senderId(senderId)
                .senderType(senderType)
                .content(request.getContent())
                .messageType(request.getMessageType())
                .status(MessageStatus.SENT)
                .build();


        /*
         * Save message to database.
         */
        Message savedMessage =
                messageRepository.save(message);


        /*
         * Update conversation activity.
         *
         * This keeps the latest active chat
         * at the top of the chat list.
         */
        conversation.touch();


        /*
         * The conversation is managed by JPA
         * inside this transaction, so the
         * updatedAt change will be persisted
         * automatically when the transaction commits.
         */


        /*
         * Convert entity to DTO.
         */
        MessageResponse response =
                chatMapper.toMessageResponse(
                        savedMessage
                );


        String receiverId;


        if (senderType == SenderType.BUYER) {


        receiverId = conversation
                .getAgent()
                .getFirebaseUid();


        } else {


        receiverId = conversation
                .getBuyer()
                .getFirebaseUid();
        }


        messageDispatcher.dispatch(
                receiverId,
                response
        );


        return response;
    }

        /**
     * Send an automatic message generated
     * by the platform.
     *
     * Examples:
     *
     * - Reservation created
     * - Reservation approved
     * - Reservation rejected
     * - Payment completed
     * - Appointment scheduled
     */
    @Override
    public MessageResponse sendSystemMessage(
            UUID conversationId,
            String content
    ) {

        /*
         * Load the conversation.
         */
        Conversation conversation =
                conversationService.getConversation(
                        conversationId
                );


        /*
         * Create a system message.
         */
        Message message = Message.builder()
                .conversation(conversation)
                .senderId(SYSTEM_USER)
                .senderType(SenderType.SYSTEM)
                .content(content)
                .messageType(MessageType.SYSTEM)
                .status(MessageStatus.SENT)
                .build();


        /*
         * Save the system message.
         */
        Message savedMessage =
                messageRepository.save(message);


        /*
         * Update conversation activity.
         * This moves the conversation
         * to the top of the chat list.
         */
        conversation.touch();


        /*
         * No explicit save needed.
         * JPA dirty checking will update
         * the Conversation entity when the
         * transaction is committed.
         */


        /*
         * Convert to response DTO.
         */
        MessageResponse response =
                chatMapper.toMessageResponse(
                        savedMessage
                );


        String buyerId = conversation
                .getBuyer()
                .getFirebaseUid();


        String agentId = conversation
                .getAgent()
                .getFirebaseUid();


        messageDispatcher.dispatch(
                buyerId,
                response
        );


        messageDispatcher.dispatch(
                agentId,
                response
        );


        return response;
    }

        @Override
        @Transactional(readOnly = true)
        public Page<MessageResponse> getMessages(
                UUID conversationId,
                String userId,
                Pageable pageable
        ) {

        /*
        * Load conversation.
        */
        Conversation conversation =
                conversationService.getConversation(
                        conversationId
                );


        /*
        * Security check.
        *
        * Only the buyer or agent belonging
        * to this conversation can view messages.
        */
        conversationService.validateParticipant(
                conversation,
                userId
        );


        /*
        * Fetch messages with pagination.
        */
        return messageRepository
                .findByConversationOrderByCreatedAtDesc(
                        conversation,
                        pageable
                )
                .map(
                        chatMapper::toMessageResponse
                );
        }

        /**
     * Mark messages from the other participant
     * as READ.
     *
     * Example:
     *
     * Agent sends a message:
     * Status = SENT
     *
     * Buyer opens the conversation:
     * Status = READ
     */
    @Override
    public void markConversationAsRead(
            UUID conversationId,
            String userId
    ) {

        /*
         * Load conversation.
         */
        Conversation conversation =
                conversationService.getConversation(
                        conversationId
                );


        /*
         * Ensure the user belongs to this conversation.
         */
        conversationService.validateParticipant(
                conversation,
                userId
        );


        /*
         * Determine which messages should be marked
         * as read.
         *
         * If buyer opens chat:
         *     Mark agent messages as READ
         *
         * If agent opens chat:
         *     Mark buyer messages as READ
         */
        SenderType senderTypeToMark;


        if (conversation.getBuyer()
                .getFirebaseUid()
                .equals(userId)) {

            senderTypeToMark = SenderType.AGENT;

        } else {

            senderTypeToMark = SenderType.BUYER;
        }


        /*
         * Find unread messages from the other side.
         */
        List<Message> unreadMessages =
                messageRepository
                        .findByConversationAndStatusAndSenderType(
                                conversation,
                                MessageStatus.SENT,
                                senderTypeToMark
                        );


        /*
         * Change status to READ.
         */
        for (Message message : unreadMessages) {

            message.setStatus(
                    MessageStatus.READ
            );
        }


        /*
         * Save all updated messages.
         */
        messageRepository.saveAll(
                unreadMessages
        );
    }

}