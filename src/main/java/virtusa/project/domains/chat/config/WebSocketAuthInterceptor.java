package virtusa.project.domains.chat.config;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


@Component
public class WebSocketAuthInterceptor
        implements ChannelInterceptor {


    @Override
    public Message<?> preSend(
            Message<?> message,
            MessageChannel channel
    ) {


        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(message);


        /*
         * Only authenticate when client connects.
         */
        if (StompCommand.CONNECT.equals(
                accessor.getCommand()
        )) {


            String header =
                    accessor.getFirstNativeHeader(
                            "Authorization"
                    );


            if (header == null ||
                    !header.startsWith("Bearer ")) {


                throw new IllegalArgumentException(
                        "Missing Firebase token"
                );
            }


            String idToken =
                    header.substring(7);


            try {


                FirebaseToken decodedToken =
                        FirebaseAuth
                                .getInstance()
                                .verifyIdToken(idToken);


                String firebaseUid =
                        decodedToken.getUid();


                /*
                 * Store the authenticated user
                 * in the WebSocket session.
                 *
                 * Later we can access it with:
                 *
                 * Principal principal
                 * principal.getName()
                 */
                accessor.setUser(
                        () -> firebaseUid
                );


            } catch (FirebaseAuthException e) {


                throw new IllegalArgumentException(
                        "Invalid Firebase token",
                        e
                );
            }
        }


        return message;
    }
}