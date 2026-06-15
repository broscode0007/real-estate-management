package virtusa.project.domains.buyers.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WishlistResponse {

    private final UUID id;

    private final UUID propertyId;

    private final LocalDateTime createdAt;
}