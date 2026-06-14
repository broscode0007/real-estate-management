package virtusa.project.domains.listings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import virtusa.project.domains.listings.model.Property;

public interface PropertyRepository
        extends JpaRepository<Property, Long>,
                JpaSpecificationExecutor<Property> {

    List<Property> findByAgentFirebaseUid(String firebaseUid);

}