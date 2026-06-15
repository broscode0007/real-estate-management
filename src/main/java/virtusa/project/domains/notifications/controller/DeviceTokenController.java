package virtusa.project.domains.notifications.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import virtusa.project.domains.notifications.dto.DeviceTokenResponse;
import virtusa.project.domains.notifications.dto.RegisterDeviceTokenRequest;
import virtusa.project.domains.notifications.service.DeviceTokenService;

@RestController
@RequestMapping("/api/v1/device-tokens")
@RequiredArgsConstructor
public class DeviceTokenController {

    private final DeviceTokenService deviceTokenService;

    @PostMapping("/register")
    public ResponseEntity<DeviceTokenResponse> registerDeviceToken(
            Authentication authentication,
            @Valid @RequestBody RegisterDeviceTokenRequest request) {

        return ResponseEntity.ok(
                deviceTokenService.registerDeviceToken(
                        authentication,
                        request));
    }

    @GetMapping
    public ResponseEntity<List<DeviceTokenResponse>> getMyDevices(
            Authentication authentication) {

        return ResponseEntity.ok(
                deviceTokenService.getMyDevices(
                        authentication));
    }

    @DeleteMapping("/{fcmToken}")
    public ResponseEntity<Void> removeDeviceToken(
            Authentication authentication,
            @PathVariable String fcmToken) {

        deviceTokenService.removeDeviceToken(
                authentication,
                fcmToken);

        return ResponseEntity.noContent().build();
    }
}