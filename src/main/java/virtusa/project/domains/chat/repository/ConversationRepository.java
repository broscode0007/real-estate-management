package virtusa.project.domains.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.chat.model.Conversation;
import virtusa.project.domains.listings.model.Property;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ConversationRepository extends JpaRepository<Conversation, UUID> {


    /**
     * Find the conversation for a specific
     * buyer, agent and property.
     */
    Optional<Conversation> findByBuyerAndAgentAndProperty(
            Buyer buyer,
            Agent agent,
            Property property
    );


    /**
     * Get all conversations of a buyer.
     * Latest active chats come first.
     */
    List<Conversation> findByBuyerOrderByUpdatedAtDesc(
            Buyer buyer
    );


    /**
     * Get all conversations of an agent.
     * Latest active chats come first.
     */
    List<Conversation> findByAgentOrderByUpdatedAtDesc(
            Agent agent
    );

}