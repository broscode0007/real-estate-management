package virtusa.project.domains.notifications.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import virtusa.project.domains.notifications.dto.DeviceTokenResponse;
import virtusa.project.domains.notifications.dto.RegisterDeviceTokenRequest;

public interface DeviceTokenService {

    DeviceTokenResponse registerDeviceToken(
            Authentication authentication,
            RegisterDeviceTokenRequest request
    );


    List<DeviceTokenResponse> getMyDevices(
            Authentication authentication
    );


    void removeDeviceToken(
            Authentication authentication,
            String fcmToken
    );


    /**
     * Used internally by FCM services.
     */
    List<String> getDeviceTokensByUserId(
            String userId
    );
}