package virtusa.project.domains.buyers.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyerSyncResponse {

    private final String firebaseUid;

    private final String email;

    private final String displayName;

    private final String profilePictureUrl;

    private final boolean emailVerified;

    private final boolean active;

    private final boolean deleted;

    private final boolean profileCompleted;
}