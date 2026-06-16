package virtusa.project.domains.chat.websocket;

import java.util.Set;


/**
 * Tracks users currently connected
 * to the WebSocket server.
 *
 * Uses two-way mapping:
 *
 * userId    -> sessionId
 * sessionId -> userId
 */
public interface PresenceService {


    /**
     * Called when a user connects.
     *
     * Stores both:
     *
     * userId -> sessionId
     * sessionId -> userId
     */
    void markOnline(
            String userId,
            String sessionId
    );


    /**
     * Called when a WebSocket session closes.
     *
     * Since disconnect events reliably provide
     * the session ID, we use it to locate
     * and remove the user.
     */
    void markOfflineBySession(
            String sessionId
    );


    /**
     * Check whether a user is currently online.
     */
    boolean isOnline(
            String userId
    );


    /**
     * Get the session ID belonging to a user.
     */
    String getSessionId(
            String userId
    );


    /**
     * Find a user from a session ID.
     */
    String getUserBySession(
            String sessionId
    );


    /**
     * List all online users.
     *
     * Useful for:
     * - debugging
     * - monitoring
     * - admin dashboards
     */
    Set<String> getOnlineUsers();

}