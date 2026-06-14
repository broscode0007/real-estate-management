package virtusa.project.domains.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.dto.AgentProfileUpdateRequest;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.service.AgentProfileService;

@RestController
@RequestMapping("/api/v1/agents/profile")
@RequiredArgsConstructor
public class AgentProfileController {

    private final AgentProfileService agentProfileService;

    @GetMapping
    public ResponseEntity<Agent> getProfile(
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {

        String firebaseUid = getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                agentProfileService.getProfile(firebaseUid)
        );
    }

    @PutMapping
    public ResponseEntity<Agent> updateProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AgentProfileUpdateRequest request)
            throws Exception {

        String firebaseUid = getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                agentProfileService.updateProfile(firebaseUid, request)
        );
    }

    @PatchMapping
    public ResponseEntity<Agent> patchProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AgentProfileUpdateRequest request)
            throws Exception {

        String firebaseUid = getFirebaseUid(authorizationHeader);

        return ResponseEntity.ok(
                agentProfileService.patchProfile(firebaseUid, request)
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {

        String firebaseUid = getFirebaseUid(authorizationHeader);

        agentProfileService.deleteProfile(firebaseUid);

        return ResponseEntity.noContent().build();
    }

    private String getFirebaseUid(String authorizationHeader)
            throws Exception {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            throw new IllegalArgumentException(
                    "Missing or invalid Authorization header");
        }

        String idToken = authorizationHeader.substring(7);

        return agentProfileService.getFirebaseUidFromToken(idToken);
    }
}