package virtusa.project.domains.listings.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import virtusa.project.domains.listings.model.Property;

public interface PropertyRepository extends
        JpaRepository<Property, UUID>,
        JpaSpecificationExecutor<Property> {


    /**
     * Get properties owned by an agent
     */
    Page<Property> findByAgentFirebaseUid(
            String firebaseUid,
            Pageable pageable);


    /**
     * Future examples:
     *
     * Optional<Property> findByPropertyCode(String code);
     *
     * boolean existsByPropertyCode(String code);
     */
}