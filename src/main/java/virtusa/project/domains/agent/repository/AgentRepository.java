package virtusa.project.domains.agent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import virtusa.project.domains.agent.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    // Allows us to quickly look up an agent profile when they pass their authentication token
    Optional<Agent> findByFirebaseUid(String firebaseUid);
}