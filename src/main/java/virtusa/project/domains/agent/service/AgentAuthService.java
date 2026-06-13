package virtusa.project.domains.agent.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.model.AgentStatus;
import virtusa.project.domains.agent.repository.AgentRepository;

@Service
@RequiredArgsConstructor
public class AgentAuthService {

    private final AgentRepository agentRepository;

    @Transactional
    public Agent verifyAndSyncAgent(String idToken) throws Exception {

        FirebaseToken decodedToken =
                FirebaseAuth.getInstance().verifyIdToken(idToken);

        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();

        String displayName =
                (String) decodedToken.getClaims().get("name");

        String picture =
                (String) decodedToken.getClaims().get("picture");

        String phoneNumber =
                (String) decodedToken.getClaims().get("phone_number");

        boolean emailVerified =
                decodedToken.isEmailVerified();

        Agent agent = agentRepository.findById(uid)
                .orElseGet(() -> {

                    Agent newAgent = new Agent();

                    newAgent.setFirebaseUid(uid);
                    newAgent.setStatus(AgentStatus.PENDING);

                    return newAgent;
                });

        // Sync Firebase data every login

        agent.setEmail(email);
        agent.setEmailVerified(emailVerified);
        agent.setDisplayName(displayName);

        if (displayName != null && agent.getFullName() == null) {
            agent.setFullName(displayName);
        }

        if (picture != null) {
            agent.setProfilePictureUrl(picture);
        }

        if (phoneNumber != null) {
            agent.setPhoneNumber(phoneNumber);
        }

        agent.setProviderId(
                decodedToken.getIssuer()
        );

        agent.setFirebaseLastSignInAt(LocalDateTime.now());
        agent.setLastActiveAt(LocalDateTime.now());

        return agentRepository.save(agent);
    }
}