package virtusa.project.domains.chat.controller;


import org.springframework.messaging.handler.annotation.Header;
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
     * Header:
     *
     * userId: firebase_uid
     */
    @MessageMapping("/chat/send")
    public void sendMessage(
            @Header("userId") String senderId,
            SendMessageRequest request
    ) {


    chatService.sendMessage(
            senderId,
            request
    );


        /*
         * Nothing else to do.
         *
         * The ChatService already:
         *
         * - Saves the message
         * - Finds the receiver
         * - Calls MessageDispatcher
         * - Sends WebSocket or FCM
         */
    }
}