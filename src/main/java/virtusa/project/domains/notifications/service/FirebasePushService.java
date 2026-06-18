package virtusa.project.domains.notifications.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class FirebasePushService
        implements PushNotificationService {


    private final DeviceTokenService deviceTokenService;


    /**
     * Sends a push notification to all
     * devices registered by a user.
     */
    @Override
    public void sendToUser(
            String firebaseUid,
            String title,
            String body
    ) {


        /*
         * Get all active device tokens
         */
        List<String> tokens =
                deviceTokenService
                        .getDeviceTokensByUserId(
                                firebaseUid
                        );


        /*
         * User has no devices
         */
        if (tokens.isEmpty()) {

            log.info(
                    "No active devices found for user {}",
                    firebaseUid
            );

            return;
        }


        /*
         * Send notification to every device
         */
        for (String token : tokens) {


            try {


                Message message =
                        Message.builder()
                                .setToken(token)
                                .setNotification(
                                        Notification.builder()
                                                .setTitle(title)
                                                .setBody(body)
                                                .build()
                                )
                                .build();


                String response =
                        FirebaseMessaging
                                .getInstance()
                                .send(message);


                log.info(
                        "FCM sent successfully. User: {}, Message ID: {}",
                        firebaseUid,
                        response
                );


            } catch (Exception e) {


                log.error(
                        "Failed to send FCM to user {}",
                        firebaseUid,
                        e
                );


                /*
                 * Future improvement:
                 *
                 * Remove invalid tokens.
                 * Mark devices inactive.
                 */
            }
        }
    }
}