package virtusa.project.domains.notifications.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.notifications.dto.DeviceTokenResponse;
import virtusa.project.domains.notifications.dto.RegisterDeviceTokenRequest;
import virtusa.project.domains.notifications.model.DeviceToken;
import virtusa.project.domains.notifications.repository.DeviceTokenRepository;
import virtusa.project.exceptions.ResourceNotFoundException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class DeviceTokenServiceImpl implements DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;

    @Override
    public DeviceTokenResponse registerDeviceToken(
            Authentication authentication,
            RegisterDeviceTokenRequest request) {

        String firebaseUid = getFirebaseUid(authentication);

        DeviceToken deviceToken =
                deviceTokenRepository
                        .findByFcmToken(
                                request.getFcmToken())
                        .orElse(DeviceToken.builder()
                                .fcmToken(
                                        request.getFcmToken())
                                .build());

        deviceToken.setFirebaseUid(firebaseUid);
        deviceToken.setPlatform(
                request.getPlatform());
        deviceToken.setDeviceName(
                request.getDeviceName());
        deviceToken.setActive(true);
        deviceToken.setLastSeenAt(
                LocalDateTime.now());

        deviceToken =
                deviceTokenRepository.save(
                        deviceToken);

        return mapToResponse(deviceToken);
    }

    @Override
    public List<DeviceTokenResponse> getMyDevices(
            Authentication authentication) {

        String firebaseUid =
                getFirebaseUid(authentication);

        return deviceTokenRepository
                .findByFirebaseUidAndActiveTrue(
                        firebaseUid)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void removeDeviceToken(
            Authentication authentication,
            String fcmToken) {

        String firebaseUid =
                getFirebaseUid(authentication);

        DeviceToken deviceToken =
                deviceTokenRepository
                        .findByFcmToken(fcmToken)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Device token not found"));

        if (!firebaseUid.equals(
                deviceToken.getFirebaseUid())) {

            throw new UnauthorizedException(
                    "You do not own this device token");
        }

        deviceToken.setActive(false);

        deviceTokenRepository.save(deviceToken);
    }

    private String getFirebaseUid(
            Authentication authentication) {

        if (authentication == null ||
                authentication.getName() == null) {

            throw new UnauthorizedException(
                    "Authentication required");
        }

        return authentication.getName();
    }

    private DeviceTokenResponse mapToResponse(
            DeviceToken deviceToken) {

        return DeviceTokenResponse.builder()
                .id(deviceToken.getId())
                .firebaseUid(
                        deviceToken.getFirebaseUid())
                .platform(
                        deviceToken.getPlatform())
                .deviceName(
                        deviceToken.getDeviceName())
                .active(
                        deviceToken.getActive())
                .lastSeenAt(
                        deviceToken.getLastSeenAt())
                .createdAt(
                        deviceToken.getCreatedAt())
                .build();
    }
}