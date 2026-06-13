package virtusa.project.domains.agent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import virtusa.project.domains.agent.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {

    Optional<Agent> findByFirebaseUid(String firebaseUid);

    Optional<Agent> findByEmail(String email);

    boolean existsByFirebaseUid(String firebaseUid);

    boolean existsByEmail(String email);

    boolean existsByAgentLicenseNumber(String agentLicenseNumber);
}