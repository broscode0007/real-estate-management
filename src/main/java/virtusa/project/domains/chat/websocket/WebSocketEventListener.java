package virtusa.project.domains.chat.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {


    private final PresenceService presenceService;


    /**
     * Triggered when a WebSocket connection is established.
     */
    @EventListener
    public void handleConnect(
            SessionConnectEvent event
    ) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(
                        event.getMessage()
                );


        String userId =
                accessor.getFirstNativeHeader(
                        "userId"
                );


        String sessionId =
                accessor.getSessionId();


        if (userId != null && sessionId != null) {


            presenceService.markOnline(
                    userId,
                    sessionId
            );


            log.info(
                    "WebSocket connected. User: {}, Session: {}",
                    userId,
                    sessionId
            );
        }
    }


    /**
     * Triggered when a WebSocket connection is closed.
     */
    @EventListener
    public void handleDisconnect(
            SessionDisconnectEvent event
    ) {


        String sessionId =
                event.getSessionId();


        if (sessionId != null) {


            String userId =
                    presenceService.getUserBySession(
                            sessionId
                    );


            presenceService.markOfflineBySession(
                    sessionId
            );


            log.info(
                    "WebSocket disconnected. User: {}, Session: {}",
                    userId,
                    sessionId
            );
        }
    }
}