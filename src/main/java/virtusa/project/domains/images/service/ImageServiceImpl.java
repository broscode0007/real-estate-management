package virtusa.project.domains.images.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import virtusa.project.domains.images.dto.ImageUploadResponse;
import virtusa.project.domains.images.model.Image;
import virtusa.project.domains.images.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final S3Client s3Client;
    private final ImageRepository imageRepository;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${app.base-url}")
    private String baseUrl;

    @Override
    public ImageUploadResponse upload(MultipartFile file) throws IOException {

        String extension = getFileExtension(file.getOriginalFilename());

        String s3Key =
                "images/" +
                UUID.randomUUID() +
                (extension.isBlank() ? "" : "." + extension);

        PutObjectRequest putObjectRequest =
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(s3Key)
                        .contentType(file.getContentType())
                        .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromBytes(file.getBytes())
        );

        Image image = Image.builder()
                .s3Key(s3Key)
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .sizeBytes(file.getSize())
                .build();

        image = imageRepository.save(image);

        return ImageUploadResponse.builder()
                .id(image.getId())
                .url(baseUrl + "/image/" + image.getId())
                .build();
    }

    @Override
    public String getImageUrl(UUID imageId) {

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() ->
                        new RuntimeException("Image not found"));

        return baseUrl + "/image/" + image.getId();
    }

    private String getFileExtension(String filename) {

        if (filename == null || !filename.contains(".")) {
            return "";
        }

        return filename.substring(
                filename.lastIndexOf('.') + 1
        );
    }
}