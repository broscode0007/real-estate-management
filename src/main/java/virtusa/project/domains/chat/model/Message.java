package virtusa.project.domains.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "conversation_id",
            nullable = false
    )
    private Conversation conversation;


    /**
     * Firebase UID of sender.
     */
    @Column(
            name = "sender_id",
            nullable = false
    )
    private String senderId;


    /**
     * BUYER / AGENT / SYSTEM
     */
    @Enumerated(EnumType.STRING)
    @Column(
            name = "sender_type",
            nullable = false,
            length = 20
    )
    private SenderType senderType;


    @Column(
            name = "content",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String content;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "message_type",
            nullable = false,
            length = 20
    )
    private MessageType messageType;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 20
    )
    private MessageStatus status;


    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = MessageStatus.SENT;
        }
    }
}