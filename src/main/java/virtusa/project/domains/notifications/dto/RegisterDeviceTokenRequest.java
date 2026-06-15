package virtusa.project.domains.notifications.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDeviceTokenRequest {

    @NotBlank
    private String fcmToken;

    @NotBlank
    private String platform;

    private String deviceName;
}