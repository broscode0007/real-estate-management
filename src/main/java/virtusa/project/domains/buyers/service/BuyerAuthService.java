package virtusa.project.domains.buyers.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserRecord;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.BuyerSyncResponse;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.buyers.repository.BuyerRepository;
import virtusa.project.exceptions.AccountDeletedException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class BuyerAuthService {

    private final BuyerRepository buyerRepository;

    public BuyerSyncResponse syncBuyer(Authentication authentication) {

        if (authentication == null || authentication.getName() == null) {
            throw new UnauthorizedException("Authentication required");
        }

        try {

            String firebaseUid = authentication.getName();

            Optional<Buyer> existingBuyer =
                    buyerRepository.findByFirebaseUid(firebaseUid);

            Buyer buyer;

            if (existingBuyer.isPresent()) {

                buyer = existingBuyer.get();

                if (buyer.isAccountDeleted()) {
                    throw new AccountDeletedException();
                }

            } else {

                UserRecord userRecord =
                        FirebaseAuth.getInstance().getUser(firebaseUid);

                String providerId = null;

                UserInfo[] providers = userRecord.getProviderData();

                if (providers != null && providers.length > 0) {
                    providerId = providers[0].getProviderId();
                }

                buyer = Buyer.builder()
                        .firebaseUid(firebaseUid)
                        .email(userRecord.getEmail())
                        .emailVerified(userRecord.isEmailVerified())
                        .phoneNumber(userRecord.getPhoneNumber())
                        .displayName(userRecord.getDisplayName())
                        .profilePictureUrl(userRecord.getPhotoUrl())
                        .providerId(providerId)
                        .active(true)
                        .deleted(false)
                        .profileCompleted(false)
                        .build();

                buyer = buyerRepository.save(buyer);
            }

            return BuyerSyncResponse.builder()
                    .firebaseUid(buyer.getFirebaseUid())
                    .email(buyer.getEmail())
                    .displayName(buyer.getDisplayName())
                    .profilePictureUrl(buyer.getProfilePictureUrl())
                    .emailVerified(buyer.isEmailVerified())
                    .active(buyer.isActive())
                    .deleted(buyer.isDeleted())
                    .profileCompleted(buyer.isProfileCompleted())
                    .build();

        } catch (AccountDeletedException e) {
            throw e;
        } catch (FirebaseAuthException e) {
            throw new UnauthorizedException(
                    "Failed to sync buyer account: " + e.getMessage());
        }
    }
}