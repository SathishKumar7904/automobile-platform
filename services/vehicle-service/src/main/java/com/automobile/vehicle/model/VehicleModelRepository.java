package com.automobile.vehicle.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, UUID> {

    List<VehicleModel> findAllByBrand_IdAndActiveTrueOrderByNameAsc(UUID brandId);
}