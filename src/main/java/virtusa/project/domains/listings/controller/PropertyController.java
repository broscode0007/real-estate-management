package virtusa.project.domains.listings.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;
import virtusa.project.domains.listings.service.PropertyService;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(
            @RequestHeader("X-User-Id") String agentFirebaseUid,
            @RequestBody CreatePropertyRequest request) {

        return ResponseEntity.ok(
                propertyService.createProperty(
                        agentFirebaseUid,
                        request
                )
        );
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyResponse> updateProperty(
            @PathVariable Long propertyId,
            @RequestHeader("X-User-Id") String agentFirebaseUid,
            @RequestBody UpdatePropertyRequest request) {

        return ResponseEntity.ok(
                propertyService.updateProperty(
                        propertyId,
                        agentFirebaseUid,
                        request
                )
        );
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponse> getProperty(
            @PathVariable Long propertyId) {

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
            @RequestHeader("X-User-Id") String agentFirebaseUid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return ResponseEntity.ok(
                propertyService.getAgentProperties(
                        agentFirebaseUid,
                        page,
                        size
                )
        );
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @PathVariable Long propertyId,
            @RequestHeader("X-User-Id") String agentFirebaseUid) {

        propertyService.deleteProperty(
                propertyId,
                agentFirebaseUid
        );

        return ResponseEntity.noContent().build();
    }
}