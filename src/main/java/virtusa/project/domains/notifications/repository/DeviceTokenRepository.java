package virtusa.project.domains.notifications.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.project.domains.notifications.model.DeviceToken;


public interface DeviceTokenRepository
        extends JpaRepository<DeviceToken, UUID> {

    List<DeviceToken> findByFirebaseUidAndActiveTrue(
            String firebaseUid);

    Optional<DeviceToken> findByFcmToken(
            String fcmToken);

    boolean existsByFcmToken(
            String fcmToken);

    void deleteByFcmToken(
            String fcmToken);

    long countByFirebaseUidAndActiveTrue(
            String firebaseUid);
}