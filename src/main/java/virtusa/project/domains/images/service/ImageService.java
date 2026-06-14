package virtusa.project.domains.images.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import virtusa.project.domains.images.dto.ImageUploadResponse;

public interface ImageService {

    ImageUploadResponse upload(MultipartFile file);

    byte[] getImage(UUID imageId);

    String getContentType(UUID imageId);
}