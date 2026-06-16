package virtusa.project.domains.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import virtusa.project.domains.chat.model.MessageType;

import java.util.UUID;


@Getter
@Setter
public class SendMessageRequest {


    /**
     * Existing conversation where the message belongs.
     */
    @NotNull(message = "Conversation ID is required")
    private UUID conversationId;


    /**
     * Message body.
     */
    @NotBlank(message = "Message content cannot be empty")
    private String content;


    /**
     * TEXT, IMAGE, FILE.
     */
    @NotNull(message = "Message type is required")
    private MessageType messageType;

}