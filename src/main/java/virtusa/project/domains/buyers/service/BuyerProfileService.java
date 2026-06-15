package virtusa.project.domains.buyers.service;

import org.springframework.security.core.Authentication;

import virtusa.project.domains.buyers.dto.BuyerProfilePatchRequest;
import virtusa.project.domains.buyers.dto.BuyerProfileResponse;
import virtusa.project.domains.buyers.dto.BuyerProfileUpdateRequest;

public interface BuyerProfileService {

    BuyerProfileResponse getProfile(Authentication authentication);

    BuyerProfileResponse updateProfile(
            Authentication authentication,
            BuyerProfileUpdateRequest request);

    BuyerProfileResponse patchProfile(
            Authentication authentication,
            BuyerProfilePatchRequest request);

    void deleteProfile(Authentication authentication);
}