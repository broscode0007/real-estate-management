package virtusa.project.domains.images.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import virtusa.project.domains.images.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}