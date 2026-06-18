package virtusa.project.domains.notifications.service;


/**
 * Generic push notification contract.
 *
 * Current implementation:
 * Firebase Cloud Messaging (FCM)
 *
 * Future:
 * - AWS SNS
 * - OneSignal
 * - Other providers
 */
public interface PushNotificationService {


    /**
     * Send a push notification to all
     * devices belonging to a user.
     *
     * Example:
     *
     * User:
     * firebase_uid_123
     *
     * Devices:
     * - iPhone
     * - Android tablet
     * - Browser
     */
    void sendToUser(
            String firebaseUid,
            String title,
            String body
    );

}