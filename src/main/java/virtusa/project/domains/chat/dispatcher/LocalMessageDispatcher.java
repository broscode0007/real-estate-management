package virtusa.project.domains.chat.dispatcher;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import virtusa.project.domains.chat.dto.MessageResponse;
import virtusa.project.domains.chat.websocket.PresenceService;
import virtusa.project.domains.notifications.service.PushNotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalMessageDispatcher
        implements MessageDispatcher {


    private final PresenceService presenceService;


    private final SimpMessagingTemplate messagingTemplate;

    private final PushNotificationService pushNotificationService;
                
    /**
     * Deliver a message to the receiver.
     */
    @Override
    public void dispatch(
            String receiverId,
            MessageResponse message
    ) {


        /*
         * Check whether the receiver
         * is currently connected.
         */
        if (presenceService.isOnline(receiverId)) {


            /*
             * Send message through STOMP.
             *
             * Client subscribes to:
             *
             * /user/queue/messages
             */
            messagingTemplate.convertAndSendToUser(
                    receiverId,
                    "/queue/messages",
                    message
            );


            log.info(
                    "Message delivered via WebSocket to {}",
                    receiverId
            );


        } else {


            /*
             * Temporary placeholder.
             *
             * Next step:
             * Firebase Cloud Messaging.
             */
            pushNotificationService.sendToUser(
                        receiverId,
                        "New Message",
                        message.getContent()
                );


                log.info(
                        "User {} is offline. FCM notification sent.",
                        receiverId
                );
                        }
    }
}