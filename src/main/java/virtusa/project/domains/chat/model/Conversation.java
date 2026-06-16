package virtusa.project.domains.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.listings.model.Property;


@Entity
@Table(
        name = "conversations",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "buyer_id",
                                "agent_id",
                                "property_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;


    /**
     * Buyer participating in the conversation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "buyer_id",
            nullable = false
    )
    private Buyer buyer;


    /**
     * Agent participating in the conversation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "agent_id",
            nullable = false
    )
    private Agent agent;


    /**
     * Property this conversation is about.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "property_id",
            nullable = false
    )
    private Property property;


    /**
     * When the conversation was created.
     */
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;


    /**
     * Last activity time.
     * Used for sorting conversations.
     */
    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    /**
     * Manually update conversation activity.
     */
    public void touch() {
        this.updatedAt = LocalDateTime.now();
    }
}