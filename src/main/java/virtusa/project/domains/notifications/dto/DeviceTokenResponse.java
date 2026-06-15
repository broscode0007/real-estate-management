package virtusa.project.domains.notifications.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeviceTokenResponse {

    private final UUID id;

    private final String firebaseUid;

    private final String platform;

    private final String deviceName;

    private final Boolean active;

    private final LocalDateTime lastSeenAt;

    private final LocalDateTime createdAt;
}