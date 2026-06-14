package virtusa.project.domains.images.controller;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.images.dto.ImageUploadResponse;
import virtusa.project.domains.images.service.ImageService;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> upload(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                imageService.upload(file)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable UUID id) {

        byte[] image = imageService.getImage(id);

        String contentType = imageService.getContentType(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }
}