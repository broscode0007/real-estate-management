package virtusa.project.domains.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import virtusa.project.domains.agent.repository.AgentRepository;
import virtusa.project.domains.buyers.repository.BuyerRepository;
import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.model.Conversation;
import virtusa.project.domains.chat.model.Message;
import virtusa.project.domains.chat.model.MessageStatus;
import virtusa.project.domains.chat.model.SenderType;
import virtusa.project.domains.chat.repository.ConversationRepository;
import virtusa.project.domains.chat.repository.MessageRepository;
import virtusa.project.domains.listings.repository.PropertyRepository;
import virtusa.project.exceptions.ResourceNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional
public class ConversationServiceImpl implements ConversationService {


    private final ConversationRepository conversationRepository;


    private final MessageRepository messageRepository;


    private final BuyerRepository buyerRepository;


    private final AgentRepository agentRepository;


    private final PropertyRepository propertyRepository;


    private final ChatMapper chatMapper;

        /**
     * Create a new conversation between
     * buyer, agent and property.
     *
     * If a conversation already exists,
     * return the existing conversation.
     */
    @Override
    public Conversation createConversation(
            String buyerId,
            String agentId,
            UUID propertyId
    ) {

        var buyer = buyerRepository
                .findById(buyerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Buyer not found"
                        )
                );


        var agent = agentRepository
                .findById(agentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agent not found"
                        )
                );


        var property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"
                        )
                );


        return conversationRepository
                .findByBuyerAndAgentAndProperty(
                        buyer,
                        agent,
                        property
                )
                .orElseGet(() -> {

                    Conversation conversation =
                            Conversation.builder()
                                    .buyer(buyer)
                                    .agent(agent)
                                    .property(property)
                                    .build();


                    return conversationRepository
                            .save(conversation);
                });
    }
        /**
     * Get a conversation by ID.
     *
     * Used by MessageService before performing
     * any message-related operations.
     */
    @Override
    @Transactional(readOnly = true)
    public Conversation getConversation(
            UUID conversationId
    ) {

        return conversationRepository
                .findById(conversationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Conversation not found"
                        )
                );
    }


    /**
     * Ensure that the current user is a valid
     * participant of this conversation.
     *
     * A user can only be:
     * - The buyer
     * - The agent
     *
     * Otherwise access is denied.
     */
    @Override
    public void validateParticipant(
            Conversation conversation,
            String userId
    ) {

        boolean isBuyer = conversation
                .getBuyer()
                .getFirebaseUid()
                .equals(userId);


        boolean isAgent = conversation
                .getAgent()
                .getFirebaseUid()
                .equals(userId);


        if (!isBuyer && !isAgent) {

            throw new IllegalArgumentException(
                    "User is not a participant of this conversation"
            );
        }
    }
    
    /**
     * Get all conversations for a user.
     *
     * Returns a WhatsApp-style conversation list.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConversationResponse> getUserConversations(
            String userId
    ) {

        List<Conversation> conversations;


        /*
         * Determine whether the user is a buyer or an agent.
         */
        if (buyerRepository.existsById(userId)) {

            var buyer = buyerRepository
                    .findById(userId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Buyer not found"
                            )
                    );

            conversations = conversationRepository
                    .findByBuyerOrderByUpdatedAtDesc(buyer);

        } else if (agentRepository.existsById(userId)) {

            var agent = agentRepository
                    .findById(userId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Agent not found"
                            )
                    );

            conversations = conversationRepository
                    .findByAgentOrderByUpdatedAtDesc(agent);

        } else {

            throw new ResourceNotFoundException(
                    "User not found"
            );
        }


        List<ConversationResponse> responses = new java.util.ArrayList<>();


        for (Conversation conversation : conversations) {


            boolean isBuyer = conversation
                    .getBuyer()
                    .getFirebaseUid()
                    .equals(userId);


            String participantId;
            String participantName;
            String participantImageUrl;
            SenderType unreadSenderType;


            /*
             * Get the other participant.
             */
            if (isBuyer) {

                participantId = conversation
                        .getAgent()
                        .getFirebaseUid();


                participantName = conversation
                        .getAgent()
                        .getDisplayName();


                participantImageUrl = conversation
                        .getAgent()
                        .getProfilePictureUrl();


                unreadSenderType = SenderType.AGENT;


            } else {


                participantId = conversation
                        .getBuyer()
                        .getFirebaseUid();


                participantName = conversation
                        .getBuyer()
                        .getDisplayName();


                participantImageUrl = conversation
                        .getBuyer()
                        .getProfilePictureUrl();


                unreadSenderType = SenderType.BUYER;
            }


            /*
             * Get latest message.
             */
            Message latestMessage = messageRepository
                    .findTopByConversationOrderByCreatedAtDesc(
                            conversation
                    )
                    .orElse(null);


            String lastMessage = null;

            if (latestMessage != null) {
                lastMessage = latestMessage.getContent();
            }


            /*
             * Count unread messages from the other side.
             */
            long unreadCount = messageRepository
                    .countByConversationAndStatusAndSenderType(
                            conversation,
                            MessageStatus.SENT,
                            unreadSenderType
                    );


            /*
             * Property thumbnail.
             */
            String propertyImageUrl = null;


            if (conversation.getProperty()
                    .getImageUrls() != null
                    &&
                    !conversation.getProperty()
                            .getImageUrls()
                            .isEmpty()) {


                propertyImageUrl = conversation
                        .getProperty()
                        .getImageUrls()
                        .get(0);
            }


            responses.add(
                    chatMapper.toConversationResponse(
                            conversation,
                            participantId,
                            participantName,
                            participantImageUrl,
                            propertyImageUrl,
                            lastMessage,
                            unreadCount
                    )
            );
        }


        return responses;
    }

}