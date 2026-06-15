package virtusa.project.domains.buyers.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.project.domains.buyers.model.Wishlist;


public interface WishlistRepository extends JpaRepository<Wishlist, UUID> {

    List<Wishlist> findByBuyerFirebaseUidOrderByCreatedAtDesc(
            String buyerFirebaseUid);

    Optional<Wishlist> findByBuyerFirebaseUidAndPropertyId(
            String buyerFirebaseUid,
            UUID propertyId);

    boolean existsByBuyerFirebaseUidAndPropertyId(
            String buyerFirebaseUid,
            UUID propertyId);

    void deleteByBuyerFirebaseUidAndPropertyId(
            String buyerFirebaseUid,
            UUID propertyId);

    long countByBuyerFirebaseUid(
            String buyerFirebaseUid);
}