package virtusa.project.domains.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.service.AgentAuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/agents/auth")
public class AgentAuthController {

    private final AgentAuthService agentAuthService;

    @Autowired
    public AgentAuthController(AgentAuthService agentAuthService) {
        this.agentAuthService = agentAuthService;
    }

    @PostMapping("/sync")
    public ResponseEntity<Map<String, Object>> syncAgentWithFirebase(@RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> response = new HashMap<>();

        // Expecting header to be formatted as: "Bearer <token_string>"
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.put("error", "Missing or malformed Authorization header wrapper.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String idToken = authorizationHeader.substring(7);

        try {
            Agent authenticatedAgent = agentAuthService.verifyAndSyncAgent(idToken);
            
            response.put("status", "SUCCESS");
            response.put("agentId", authenticatedAgent.getId());
            response.put("email", authenticatedAgent.getEmail());
            response.put("isEmailVerified", authenticatedAgent.isEmailVerified());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("status", "AUTH_FAILED");
            response.put("reason", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}