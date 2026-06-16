package virtusa.project.domains.chat.websocket;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;


@Service
public class InMemoryPresenceService
        implements PresenceService {


    /**
     * Firebase UID -> WebSocket session ID
     */
    private final Map<String, String> userSessions =
            new ConcurrentHashMap<>();


    /**
     * WebSocket session ID -> Firebase UID
     */
    private final Map<String, String> sessionUsers =
            new ConcurrentHashMap<>();


    /**
     * Called when a user connects.
     */
    @Override
    public void markOnline(
            String userId,
            String sessionId
    ) {

        userSessions.put(
                userId,
                sessionId
        );


        sessionUsers.put(
                sessionId,
                userId
        );
    }


    /**
     * Called when a WebSocket session disconnects.
     */
    @Override
    public void markOfflineBySession(
            String sessionId
    ) {

        String userId =
                sessionUsers.remove(
                        sessionId
                );


        if (userId != null) {

            userSessions.remove(
                    userId
            );
        }
    }


    /**
     * Check whether a user is online.
     */
    @Override
    public boolean isOnline(
            String userId
    ) {

        return userSessions.containsKey(
                userId
        );
    }


    /**
     * Get the active session ID of a user.
     */
    @Override
    public String getSessionId(
            String userId
    ) {

        return userSessions.get(
                userId
        );
    }


    /**
     * Find the user attached to a session.
     */
    @Override
    public String getUserBySession(
            String sessionId
    ) {

        return sessionUsers.get(
                sessionId
        );
    }


    /**
     * Returns all online users.
     */
    @Override
    public Set<String> getOnlineUsers() {

        return userSessions.keySet();
    }
}