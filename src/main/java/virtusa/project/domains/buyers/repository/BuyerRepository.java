package virtusa.project.domains.buyers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.project.domains.buyers.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, String> {

    Optional<Buyer> findByFirebaseUid(String firebaseUid);

    Optional<Buyer> findByEmail(String email);

    boolean existsByFirebaseUid(String firebaseUid);
}