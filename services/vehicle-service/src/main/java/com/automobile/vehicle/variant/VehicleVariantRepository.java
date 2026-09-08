package com.automobile.vehicle.variant;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleVariantRepository extends JpaRepository<VehicleVariant, UUID> {

    List<VehicleVariant> findAllByModel_IdAndActiveTrueOrderByNameAsc(UUID modelId);

    @Query("""
        SELECT v
        FROM VehicleVariant v
        WHERE v.active = true
          AND (:name IS NULL OR v.name LIKE CONCAT('%', :name, '%'))
          AND (:bodyType IS NULL OR v.bodyType = :bodyType)
          AND (:fuelType IS NULL OR v.fuelType = :fuelType)
          AND (:transmission IS NULL OR v.transmission = :transmission)
          AND (:minPrice IS NULL OR v.price >= :minPrice)
          AND (:maxPrice IS NULL OR v.price <= :maxPrice)
        ORDER BY v.name ASC
        """)
    List<VehicleVariant> searchActiveVariants(
            @Param("name") String name,
            @Param("bodyType") String bodyType,
            @Param("fuelType") String fuelType,
            @Param("transmission") String transmission,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}
