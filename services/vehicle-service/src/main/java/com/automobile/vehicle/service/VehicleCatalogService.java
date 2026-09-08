package com.automobile.vehicle.service;

import com.automobile.vehicle.brand.Brand;
import com.automobile.vehicle.brand.BrandRepository;
import com.automobile.vehicle.dto.BrandResponse;
import com.automobile.vehicle.dto.VehicleModelResponse;
import com.automobile.vehicle.dto.VehicleVariantResponse;
import com.automobile.vehicle.exception.VehicleCatalogNotFoundException;
import com.automobile.vehicle.model.VehicleModel;
import com.automobile.vehicle.model.VehicleModelRepository;
import com.automobile.vehicle.variant.VehicleVariant;
import com.automobile.vehicle.variant.VehicleVariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class VehicleCatalogService {

    private final BrandRepository brandRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleVariantRepository vehicleVariantRepository;

    public VehicleCatalogService(
            BrandRepository brandRepository,
            VehicleModelRepository vehicleModelRepository,
            VehicleVariantRepository vehicleVariantRepository
    ) {
        this.brandRepository = brandRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleVariantRepository = vehicleVariantRepository;
    }

    public List<BrandResponse> getActiveBrands() {
        return brandRepository.findAllByActiveTrueOrderByNameAsc()
                .stream()
                .map(BrandResponse::from)
                .toList();
    }

    public List<VehicleModelResponse> getActiveModelsByBrand(UUID brandId) {
        Brand brand = brandRepository.findById(brandId)
                .filter(Brand::isActive)
                .orElseThrow(() ->
                        new VehicleCatalogNotFoundException(
                                "Active brand not found: " + brandId
                        )
                );

        return vehicleModelRepository
                .findAllByBrand_IdAndActiveTrueOrderByNameAsc(brand.getId())
                .stream()
                .map(VehicleModelResponse::from)
                .toList();
    }

    public List<VehicleVariantResponse> getActiveVariantsByModel(UUID modelId) {
        VehicleModel model = vehicleModelRepository.findById(modelId)
                .filter(VehicleModel::isActive)
                .orElseThrow(() ->
                        new VehicleCatalogNotFoundException(
                                "Active vehicle model not found: " + modelId
                        )
                );

        return vehicleVariantRepository
                .findAllByModel_IdAndActiveTrueOrderByNameAsc(model.getId())
                .stream()
                .map(VehicleVariantResponse::from)
                .toList();
    }

    public VehicleVariantResponse getActiveVariant(UUID variantId) {
        VehicleVariant variant = vehicleVariantRepository.findById(variantId)
                .filter(VehicleVariant::isActive)
                .orElseThrow(() ->
                        new VehicleCatalogNotFoundException(
                                "Active vehicle variant not found: " + variantId
                        )
                );

        return VehicleVariantResponse.from(variant);
    }
}