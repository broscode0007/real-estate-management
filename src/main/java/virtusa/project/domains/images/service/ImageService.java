package virtusa.project.domains.images.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import virtusa.project.domains.images.dto.ImageUploadResponse;

public interface ImageService {

    ImageUploadResponse upload(MultipartFile file) throws IOException;

    String getImageUrl(UUID imageId);
}