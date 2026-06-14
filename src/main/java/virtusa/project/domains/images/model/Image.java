package virtusa.project.domains.images.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "images",
    indexes = {
        @Index(name = "idx_image_s3_key", columnList = "s3Key")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Object key stored in S3.
     *
     * Example:
     * properties/8c4d71f2-6d7a-4c4f-a2b1-7d23e8e5d7c1.jpg
     */
    @Column(nullable = false, unique = true, length = 500)
    private String s3Key;

    /**
     * Original uploaded filename.
     *
     * Example:
     * living-room.jpg
     */
    @Column(length = 255)
    private String originalFilename;

    /**
     * image/jpeg
     * image/png
     * image/webp
     */
    @Column(length = 100)
    private String contentType;

    /**
     * File size in bytes.
     */
    private Long sizeBytes;

    /**
     * Soft delete support.
     */
    @Builder.Default
    private Boolean deleted = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Convenience method.
     */
    @Transient
    public String getPublicUrl() {
        return "/image/" + id;
    }
}