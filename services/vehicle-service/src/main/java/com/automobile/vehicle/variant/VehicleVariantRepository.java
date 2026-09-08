package com.automobile.vehicle.variant;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleVariantRepository extends JpaRepository<VehicleVariant, UUID> {

    List<VehicleVariant> findAllByModel_IdAndActiveTrueOrderByNameAsc(UUID modelId);
}