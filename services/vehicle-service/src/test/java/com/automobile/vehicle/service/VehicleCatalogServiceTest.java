package com.automobile.vehicle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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

@ExtendWith(MockitoExtension.class)
class VehicleCatalogServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    @Mock
    private VehicleVariantRepository vehicleVariantRepository;

    private VehicleCatalogService vehicleCatalogService;

    @BeforeEach
    void setUp() {
        vehicleCatalogService = new VehicleCatalogService(
                brandRepository,
                vehicleModelRepository,
                vehicleVariantRepository
        );
    }

    private Brand brand(UUID id, String name, boolean active) {
        Brand brand = new Brand(name);
        ReflectionTestUtils.setField(brand, "id", id);
        brand.setActive(active);
        return brand;
    }

    private VehicleModel model(UUID id, Brand brand, String name, boolean active) {
        VehicleModel model = new VehicleModel(brand, name);
        ReflectionTestUtils.setField(model, "id", id);
        model.setActive(active);
        return model;
    }

    private VehicleVariant variant(UUID id, VehicleModel model, String name, boolean active) {
        VehicleVariant variant = new VehicleVariant(model, name);
        ReflectionTestUtils.setField(variant, "id", id);
        variant.setBodyType("Sedan");
        variant.setFuelType("Petrol");
        variant.setTransmission("Automatic");
        variant.setPrice(new BigDecimal("5000000.00"));
        variant.setActive(active);
        return variant;
    }

    // ---------- Brands ----------

    @Test
    void shouldReturnActiveBrands() {
        Brand bmw = brand(UUID.randomUUID(), "BMW", true);
        Brand mercedes = brand(UUID.randomUUID(), "Mercedes-Benz", true);

        when(brandRepository.findAllByActiveTrueOrderByNameAsc())
                .thenReturn(List.of(bmw, mercedes));

        List<BrandResponse> responses = vehicleCatalogService.getActiveBrands();

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).name()).isEqualTo("BMW");
        assertThat(responses.get(1).name()).isEqualTo("Mercedes-Benz");
    }

    @Test
    void shouldReturnEmptyListWhenNoActiveBrandsExist() {
        when(brandRepository.findAllByActiveTrueOrderByNameAsc())
                .thenReturn(List.of());

        List<BrandResponse> responses = vehicleCatalogService.getActiveBrands();

        assertThat(responses).isEmpty();
    }

    // ---------- Models by brand ----------

    @Test
    void shouldReturnActiveModelsForActiveBrand() {
        UUID brandId = UUID.randomUUID();
        Brand bmw = brand(brandId, "BMW", true);
        VehicleModel series3 = model(UUID.randomUUID(), bmw, "3 Series", true);

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(bmw));
        when(vehicleModelRepository.findAllByBrand_IdAndActiveTrueOrderByNameAsc(brandId))
                .thenReturn(List.of(series3));

        List<VehicleModelResponse> responses =
                vehicleCatalogService.getActiveModelsByBrand(brandId);

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).name()).isEqualTo("3 Series");
        assertThat(responses.get(0).brandId()).isEqualTo(brandId);
    }

    @Test
    void shouldThrowWhenBrandNotFound() {
        UUID brandId = UUID.randomUUID();

        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveModelsByBrand(brandId)
        );
    }

    @Test
    void shouldThrowWhenBrandIsInactive() {
        UUID brandId = UUID.randomUUID();
        Brand inactiveBrand = brand(brandId, "Discontinued Brand", false);

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(inactiveBrand));

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveModelsByBrand(brandId)
        );
    }

    // ---------- Variants by model ----------

    @Test
    void shouldReturnActiveVariantsForActiveModel() {
        UUID brandId = UUID.randomUUID();
        UUID modelId = UUID.randomUUID();
        Brand bmw = brand(brandId, "BMW", true);
        VehicleModel series3 = model(modelId, bmw, "3 Series", true);
        VehicleVariant variant330i = variant(UUID.randomUUID(), series3, "330i", true);

        when(vehicleModelRepository.findById(modelId)).thenReturn(Optional.of(series3));
        when(vehicleVariantRepository.findAllByModel_IdAndActiveTrueOrderByNameAsc(modelId))
                .thenReturn(List.of(variant330i));

        List<VehicleVariantResponse> responses =
                vehicleCatalogService.getActiveVariantsByModel(modelId);

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).name()).isEqualTo("330i");
        assertThat(responses.get(0).modelId()).isEqualTo(modelId);
    }

    @Test
    void shouldThrowWhenModelNotFound() {
        UUID modelId = UUID.randomUUID();

        when(vehicleModelRepository.findById(modelId)).thenReturn(Optional.empty());

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveVariantsByModel(modelId)
        );
    }

    @Test
    void shouldThrowWhenModelIsInactive() {
        UUID brandId = UUID.randomUUID();
        UUID modelId = UUID.randomUUID();
        Brand bmw = brand(brandId, "BMW", true);
        VehicleModel inactiveModel = model(modelId, bmw, "Discontinued Model", false);

        when(vehicleModelRepository.findById(modelId)).thenReturn(Optional.of(inactiveModel));

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveVariantsByModel(modelId)
        );
    }

    // ---------- Variant details ----------

    @Test
    void shouldReturnActiveVariantDetails() {
        UUID brandId = UUID.randomUUID();
        UUID modelId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();

        Brand bmw = brand(brandId, "BMW", true);
        VehicleModel series3 = model(modelId, bmw, "3 Series", true);
        VehicleVariant variant330i = variant(variantId, series3, "330i", true);

        when(vehicleVariantRepository.findById(variantId)).thenReturn(Optional.of(variant330i));

        VehicleVariantResponse response = vehicleCatalogService.getActiveVariant(variantId);

        assertThat(response.id()).isEqualTo(variantId);
        assertThat(response.name()).isEqualTo("330i");
        assertThat(response.modelId()).isEqualTo(modelId);
    }

    @Test
    void shouldThrowWhenVariantNotFound() {
        UUID variantId = UUID.randomUUID();

        when(vehicleVariantRepository.findById(variantId)).thenReturn(Optional.empty());

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveVariant(variantId)
        );
    }

    @Test
    void shouldThrowWhenVariantIsInactive() {
        UUID brandId = UUID.randomUUID();
        UUID modelId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();

        Brand bmw = brand(brandId, "BMW", true);
        VehicleModel series3 = model(modelId, bmw, "3 Series", true);
        VehicleVariant inactiveVariant = variant(variantId, series3, "Discontinued Variant", false);

        when(vehicleVariantRepository.findById(variantId)).thenReturn(Optional.of(inactiveVariant));

        assertThrows(
                VehicleCatalogNotFoundException.class,
                () -> vehicleCatalogService.getActiveVariant(variantId)
        );
    }
}