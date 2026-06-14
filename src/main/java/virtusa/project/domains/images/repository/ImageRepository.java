package virtusa.project.domains.images.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.project.domains.images.model.Image;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}