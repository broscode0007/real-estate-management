package virtusa.project.domains.listings.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.service.AgentProfileService;
import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;
import virtusa.project.domains.listings.service.PropertyService;

@RestController
@RequestMapping("/api/v1/agents/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final AgentProfileService agentProfileService;

    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreatePropertyRequest request)
            throws Exception {

        String firebaseUid =
                getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                propertyService.createProperty(
                        firebaseUid,
                        request
                )
        );
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyResponse> updateProperty(
            @PathVariable UUID propertyId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdatePropertyRequest request)
            throws Exception {

        String firebaseUid =
                getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                propertyService.updateProperty(
                        propertyId,
                        firebaseUid,
                        request
                )
        );
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponse> getProperty(
            @PathVariable UUID propertyId) {

        return ResponseEntity.ok(
                propertyService.getProperty(propertyId)
        );
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PropertySummaryResponse>> searchProperties(
            @RequestBody PropertySearchRequest request) {

        return ResponseEntity.ok(
                propertyService.searchProperties(request)
        );
    }

    @GetMapping("/mine")
    public ResponseEntity<Page<PropertySummaryResponse>> getMyProperties(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size)
            throws Exception {

        String firebaseUid =
                getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                propertyService.getAgentProperties(
                        firebaseUid,
                        page,
                        size
                )
        );
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteProperty(
            @PathVariable UUID propertyId,
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {

        String firebaseUid =
                getFirebaseUid(authorizationHeader);

        propertyService.deleteProperty(
                propertyId,
                firebaseUid
        );

        return ResponseEntity.ok(
        "Property deactivated successfully");
    }
    @PatchMapping("/{propertyId}/deactivate")
        public ResponseEntity<Void> deactivateProperty(
                @PathVariable UUID propertyId,
                @RequestHeader("Authorization")
                String authorizationHeader)
                throws Exception {

        String firebaseUid =
                getFirebaseUid(
                        authorizationHeader);

        propertyService.deactivateProperty(
                propertyId,
                firebaseUid);

        return ResponseEntity.noContent()
                .build();
        }
        @PatchMapping("/{propertyId}/submit")
        public ResponseEntity<String> submitProperty(
                @PathVariable UUID propertyId,
                @RequestHeader("Authorization")
                String authorizationHeader)
                throws Exception {

        String firebaseUid =
                getFirebaseUid(
                        authorizationHeader);

        propertyService.submitProperty(
                propertyId,
                firebaseUid);

        return ResponseEntity.ok(
                "Property submitted successfully");
        }
    private String getFirebaseUid(
            String authorizationHeader)
            throws Exception {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            throw new IllegalArgumentException(
                    "Missing or invalid Authorization header");
        }

        String idToken =
                authorizationHeader.substring(7);

        return agentProfileService
                .getFirebaseUidFromToken(idToken);
    }
}