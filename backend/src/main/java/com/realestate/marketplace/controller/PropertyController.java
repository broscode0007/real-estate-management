package com.realestate.marketplace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @GetMapping("/search")
    public ResponseEntity<List<PropertyDto>> searchProperties(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer bhk) {
        
        List<PropertyDto> properties = new ArrayList<>();
        properties.add(new PropertyDto(
                "1L",
                "Luxury 3 BHK Apartment in Downtown",
                "Beautiful apartment with city view and amenities.",
                450000.0,
                "Apartment",
                3,
                1500.0,
                "Downtown",
                "Active"
        ));
        properties.add(new PropertyDto(
                "2L",
                "Cozy 2 BHK Villa",
                "Spacious villa with private garden.",
                850000.0,
                "Villa",
                2,
                2200.0,
                "Green Valley",
                "Active"
        ));
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable String id) {
        PropertyDto property = new PropertyDto(
                id,
                "Mock Property Detail",
                "Detailed description of the mock property.",
                500000.0,
                "Apartment",
                3,
                1600.0,
                "Suburbs",
                "Active"
        );
        return ResponseEntity.ok(property);
    }

    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto) {
        propertyDto.setId("new-id-123");
        propertyDto.setStatus("Pending Admin Review");
        return ResponseEntity.ok(propertyDto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updatePropertyStatus(
            @PathVariable String id,
            @RequestParam String status) {
        return ResponseEntity.ok().build();
    }

    public static class PropertyDto {
        private String id;
        private String title;
        private String description;
        private Double price;
        private String type;
        private Integer bhk;
        private Double areaSqFt;
        private String locality;
        private String status;

        public PropertyDto() {}

        public PropertyDto(String id, String title, String description, Double price, String type, Integer bhk, Double areaSqFt, String locality, String status) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
            this.type = type;
            this.bhk = bhk;
            this.areaSqFt = areaSqFt;
            this.locality = locality;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getBhk() {
            return bhk;
        }

        public void setBhk(Integer bhk) {
            this.bhk = bhk;
        }

        public Double getAreaSqFt() {
            return areaSqFt;
        }

        public void setAreaSqFt(Double areaSqFt) {
            this.areaSqFt = areaSqFt;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

