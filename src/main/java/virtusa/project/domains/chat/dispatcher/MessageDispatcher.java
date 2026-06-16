package virtusa.project.domains.chat.dispatcher;

import virtusa.project.domains.chat.dto.MessageResponse;


/**
 * Responsible for delivering messages
 * after they are saved in the database.
 */
public interface MessageDispatcher {


    /**
     * Deliver a chat message.
     *
     * Decision:
     *
     * User online  -> WebSocket
     *
     * User offline -> Firebase FCM
     */
    void dispatch(
            String receiverId,
            MessageResponse message
    );

}