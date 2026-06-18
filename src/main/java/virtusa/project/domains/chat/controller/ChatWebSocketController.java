package virtusa.project.domains.chat.controller;


import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.chat.dto.SendMessageRequest;
import virtusa.project.domains.chat.service.ChatService;


@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {


    private final ChatService chatService;


    /**
     * Client sends:
     *
     * /app/chat/send
     *
     * The sender is extracted from the
     * authenticated WebSocket Principal.
     */
    @MessageMapping("/chat/send")
    public void sendMessage(
            Principal principal,
            SendMessageRequest request
    ) {


        chatService.sendMessage(
                principal.getName(),
                request
        );


        /*
         * ChatService handles:
         *
         * - Validating the sender belongs to the conversation
         * - Saving the message
         * - Finding the receiver
         * - Dispatching the message
         *
         * Delivery:
         * - WebSocket if receiver is online
         * - Firebase FCM if receiver is offline
         */
    }
}