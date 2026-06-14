package virtusa.project.domains.images.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

import software.amazon.awssdk.core.ResponseInputStream;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
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

    @Override
    public ImageUploadResponse upload(MultipartFile file) {

        try {

            String key =
                    UUID.randomUUID() + "_" +
                    file.getOriginalFilename();

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromBytes(file.getBytes())
            );

            Image image = imageRepository.save(
                    Image.builder()
                            .s3Key(key)
                            .contentType(file.getContentType())
                            .size(file.getSize())
                            .originalFileName(file.getOriginalFilename())
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            return ImageUploadResponse.builder()
                    .id(image.getId())
                    .url("/images/" + image.getId())
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public byte[] getImage(UUID imageId) {

        Image image = imageRepository.findById(imageId)
                .orElseThrow();

        GetObjectRequest request =
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(image.getS3Key())
                        .build();

        try (ResponseInputStream<GetObjectResponse> stream =
                        s3Client.getObject(request)) {

                return stream.readAllBytes();

        } catch (IOException e) {
                throw new RuntimeException(e);
        }
        }

        @Override
        public String getContentType(UUID imageId) {

        return imageRepository.findById(imageId)
                .orElseThrow()
                .getContentType();
        }
}