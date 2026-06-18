package virtusa.project.domains.chat.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import virtusa.project.domains.chat.dto.ConversationResponse;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.service.ChatService;


@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {


    private final ChatService chatService;
    
        /**
     * Get all conversations for the current user.
     *
     * Mobile sends the Firebase UID in the header.
     *
     * Example:
     *
     * GET /api/chat/conversations
     *
     * Header:
     * userId: firebase_uid_123
     */
@GetMapping("/conversations")
public List<ConversationResponse> getConversations(
        Authentication authentication
) {

        return chatService.getUserConversations(
                authentication.getName()
        );
}

        /**
     * Get paginated messages of a conversation.
     *
     * Example:
     *
     * GET /api/chat/{conversationId}/messages?page=0&size=50
     */
@GetMapping("/{conversationId}/messages")
public Page<MessageResponse> getMessages(
        @PathVariable UUID conversationId,

        @RequestParam(defaultValue = "0")
        int page,

        @RequestParam(defaultValue = "50")
        int size,

        Authentication authentication
) {

    return chatService.getMessages(
            conversationId,
            authentication.getName(),
            PageRequest.of(page, size)
    );
}

        /**
     * Marks all unread messages from the
     * other participant as read.
     *
     * Example:
     *
     * PUT /api/chat/{conversationId}/read
     *
     * Header:
     * userId: firebase_uid_123
     */
        @PutMapping("/{conversationId}/read")
        public void markAsRead(
                @PathVariable UUID conversationId,
                Authentication authentication
        ) {

        chatService.markConversationAsRead(
                conversationId,
                authentication.getName()
        );
        }
}