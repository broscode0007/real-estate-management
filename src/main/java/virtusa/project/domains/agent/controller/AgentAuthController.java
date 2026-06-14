package virtusa.project.domains.agent.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.service.AgentAuthService;

@RestController
@RequestMapping("/api/v1/agents/auth")
@RequiredArgsConstructor
public class AgentAuthController {

    private final AgentAuthService agentAuthService;

    @PostMapping("/sync")
    public ResponseEntity<Map<String, Object>> syncAgentWithFirebase(
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            throw new IllegalArgumentException(
                    "Missing Authorization header");
        }

        String idToken = authorizationHeader.substring(7);

        Agent agent =
                agentAuthService.verifyAndSyncAgent(idToken);

        boolean profileCompleted =
                agent.getAgencyName() != null &&
                agent.getAgentLicenseNumber() != null &&
                agent.getCity() != null &&
                agent.getState() != null &&
                agent.getCountry() != null;

        Map<String, Object> response = new HashMap<>();

        response.put("firebaseUid",
                agent.getFirebaseUid());

        response.put("email",
                agent.getEmail());

        response.put("fullName",
                agent.getFullName());

        response.put("emailVerified",
                agent.isEmailVerified());

        response.put("agentStatus",
                agent.getStatus());

        response.put("profileCompleted",
                profileCompleted);

        response.put("active",
                agent.isActive());

        return ResponseEntity.ok(response);
    }
}