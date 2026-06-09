package virtusa.project.domains.agent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.repository.AgentRepository;

@Service
public class AgentAuthService {

    private final AgentRepository agentRepository;

    @Autowired
    public AgentAuthService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Transactional
    public Agent verifyAndSyncAgent(String idToken) throws Exception {
        // 1. Verify the token validity against Google Firebase Servers
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        
        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = (String) decodedToken.getClaims().get("name");
        String picture = (String) decodedToken.getClaims().get("picture");
        boolean isEmailVerified = decodedToken.isEmailVerified();

        // 2. Check if this Agent is already tracked inside our local PostgreSQL schema
        Optional<Agent> existingAgent = agentRepository.findByFirebaseUid(uid);

        if (existingAgent.isPresent()) {
            Agent agent = existingAgent.get();
            // Sync current authentication state details dynamically
            agent.setEmailVerified(isEmailVerified);
            if (name != null) agent.setFullName(name);
            if (picture != null) agent.setProfilePictureUrl(picture);
            return agentRepository.save(agent);
        } else {
            // 3. First time signing up via Google/Apple ID OAuth! Provision record.
            Agent newAgent = new Agent(uid, email, name, picture, isEmailVerified);
            return agentRepository.save(newAgent);
        }
    }
}