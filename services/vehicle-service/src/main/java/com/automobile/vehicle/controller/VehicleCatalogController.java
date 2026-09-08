package com.automobile.vehicle.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.vehicle.dto.BrandResponse;
import com.automobile.vehicle.dto.VehicleModelResponse;
import com.automobile.vehicle.dto.VehicleVariantResponse;
import com.automobile.vehicle.service.VehicleCatalogService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleCatalogController {

    private final VehicleCatalogService vehicleCatalogService;

    public VehicleCatalogController(VehicleCatalogService vehicleCatalogService) {
        this.vehicleCatalogService = vehicleCatalogService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands() {
        return ResponseEntity.ok(vehicleCatalogService.getActiveBrands());
    }

    @GetMapping("/brands/{brandId}/models")
    public ResponseEntity<List<VehicleModelResponse>> getModels(
            @PathVariable @NotNull UUID brandId
    ) {
        return ResponseEntity.ok(
                vehicleCatalogService.getActiveModelsByBrand(brandId)
        );
    }

    @GetMapping("/models/{modelId}/variants")
    public ResponseEntity<List<VehicleVariantResponse>> getVariants(
            @PathVariable @NotNull UUID modelId
    ) {
        return ResponseEntity.ok(
                vehicleCatalogService.getActiveVariantsByModel(modelId)
        );
    }

    @GetMapping("/variants/{variantId}")
    public ResponseEntity<VehicleVariantResponse> getVariant(
            @PathVariable @NotNull UUID variantId
    ) {
        return ResponseEntity.ok(
                vehicleCatalogService.getActiveVariant(variantId)
        );
    }
}