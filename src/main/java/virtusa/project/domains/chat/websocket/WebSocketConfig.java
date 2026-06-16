package virtusa.project.domains.chat.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig
        implements WebSocketMessageBrokerConfigurer {


    /**
     * Register WebSocket endpoint.
     *
     * Mobile applications connect here.
     */
    @Override
    public void registerStompEndpoints(
            StompEndpointRegistry registry
    ) {

        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*");

    }


    /**
     * Configure STOMP routes.
     */
    @Override
    public void configureMessageBroker(
            MessageBrokerRegistry registry
    ) {


        /*
         * Client -> Server
         *
         * Example:
         * /app/chat/send
         */
        registry.setApplicationDestinationPrefixes(
                "/app"
        );


        /*
         * Server -> Client
         *
         * Example:
         * /user/queue/messages
         */
        registry.enableSimpleBroker(
                "/topic",
                "/queue"
        );


        /*
         * Enables:
         *
         * /user/{firebaseUid}/queue/messages
         */
        registry.setUserDestinationPrefix(
                "/user"
        );
    }
}