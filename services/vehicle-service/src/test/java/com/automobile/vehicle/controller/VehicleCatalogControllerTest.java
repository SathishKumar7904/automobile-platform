package com.automobile.vehicle.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.automobile.vehicle.dto.BrandResponse;
import com.automobile.vehicle.dto.VehicleModelResponse;
import com.automobile.vehicle.dto.VehicleVariantResponse;
import com.automobile.vehicle.exception.GlobalExceptionHandler;
import com.automobile.vehicle.exception.VehicleCatalogNotFoundException;
import com.automobile.vehicle.service.VehicleCatalogService;

@WebMvcTest(VehicleCatalogController.class)
@Import(GlobalExceptionHandler.class)
class VehicleCatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehicleCatalogService vehicleCatalogService;

    // ---------- Brands ----------

    @Test
    void shouldReturnBrands() throws Exception {
        when(vehicleCatalogService.getActiveBrands())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/vehicles/brands"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnOnlyActiveBrandsWithCorrectStructure() throws Exception {
        UUID bmwId = UUID.randomUUID();
        UUID mercedesId = UUID.randomUUID();

        // The service is responsible for excluding inactive brands. This
        // test asserts that whatever the (mocked) service returns is what
        // the controller exposes, with the expected JSON shape.
        when(vehicleCatalogService.getActiveBrands())
                .thenReturn(List.of(
                        new BrandResponse(bmwId, "BMW"),
                        new BrandResponse(mercedesId, "Mercedes-Benz")
                ));

        mockMvc.perform(get("/api/vehicles/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(bmwId.toString()))
                .andExpect(jsonPath("$[0].name").value("BMW"))
                .andExpect(jsonPath("$[1].id").value(mercedesId.toString()))
                .andExpect(jsonPath("$[1].name").value("Mercedes-Benz"));
    }

    // ---------- Models by brand ----------

    @Test
    void shouldReturnModels() throws Exception {
        UUID brandId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveModelsByBrand(brandId))
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/vehicles/brands/{brandId}/models", brandId)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnOnlyActiveModelsForBrand() throws Exception {
        UUID brandId = UUID.randomUUID();
        UUID modelId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveModelsByBrand(brandId))
                .thenReturn(List.of(
                        new VehicleModelResponse(modelId, brandId, "3 Series")
                ));

        mockMvc.perform(
                get("/api/vehicles/brands/{brandId}/models", brandId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(modelId.toString()))
                .andExpect(jsonPath("$[0].brandId").value(brandId.toString()))
                .andExpect(jsonPath("$[0].name").value("3 Series"));
    }

    @Test
    void shouldReturnNotFoundForNonexistentOrInactiveBrand() throws Exception {
        UUID brandId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveModelsByBrand(brandId))
                .thenThrow(
                        new VehicleCatalogNotFoundException(
                                "Active brand not found: " + brandId
                        )
                );

        mockMvc.perform(
                get("/api/vehicles/brands/{brandId}/models", brandId)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void shouldReturnBadRequestForInvalidBrandUuid() throws Exception {
        mockMvc.perform(
                get("/api/vehicles/brands/{brandId}/models",
                        "not-a-valid-uuid")
        )
                .andExpect(status().isBadRequest());
    }

    // ---------- Variants by model ----------

    @Test
    void shouldReturnVariants() throws Exception {
        UUID modelId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveVariantsByModel(modelId))
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/vehicles/models/{modelId}/variants", modelId)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnOnlyActiveVariantsForModel() throws Exception {
        UUID modelId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveVariantsByModel(modelId))
                .thenReturn(List.of(
                        new VehicleVariantResponse(
                                variantId,
                                modelId,
                                "330i",
                                "Sedan",
                                "Petrol",
                                "Automatic",
                                new BigDecimal("6500000.00")
                        )
                ));

        mockMvc.perform(
                get("/api/vehicles/models/{modelId}/variants", modelId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(variantId.toString()))
                .andExpect(jsonPath("$[0].modelId").value(modelId.toString()))
                .andExpect(jsonPath("$[0].name").value("330i"));
    }

    @Test
    void shouldReturnNotFoundForNonexistentOrInactiveModel() throws Exception {
        UUID modelId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveVariantsByModel(modelId))
                .thenThrow(
                        new VehicleCatalogNotFoundException(
                                "Active vehicle model not found: " + modelId
                        )
                );

        mockMvc.perform(
                get("/api/vehicles/models/{modelId}/variants", modelId)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void shouldReturnBadRequestForInvalidModelUuid() throws Exception {
        mockMvc.perform(
                get("/api/vehicles/models/{modelId}/variants",
                        "not-a-valid-uuid")
        )
                .andExpect(status().isBadRequest());
    }

    // ---------- Variant details ----------

    @Test
    void shouldReturnVariant() throws Exception {
        UUID variantId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveVariant(variantId))
                .thenThrow(
                        new VehicleCatalogNotFoundException(
                                "Active vehicle variant not found: " + variantId
                        )
                );

        mockMvc.perform(
                get("/api/vehicles/variants/{variantId}", variantId)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void shouldReturnVariantDetailsForActiveVariant() throws Exception {
        UUID modelId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();

        when(vehicleCatalogService.getActiveVariant(variantId))
                .thenReturn(new VehicleVariantResponse(
                        variantId,
                        modelId,
                        "330i",
                        "Sedan",
                        "Petrol",
                        "Automatic",
                        new BigDecimal("6500000.00")
                ));

        mockMvc.perform(
                get("/api/vehicles/variants/{variantId}", variantId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(variantId.toString()))
                .andExpect(jsonPath("$.modelId").value(modelId.toString()))
                .andExpect(jsonPath("$.name").value("330i"))
                .andExpect(jsonPath("$.bodyType").value("Sedan"))
                .andExpect(jsonPath("$.fuelType").value("Petrol"))
                .andExpect(jsonPath("$.transmission").value("Automatic"));
    }

    @Test
    void shouldReturnBadRequestForInvalidVariantUuid() throws Exception {
        mockMvc.perform(
                get("/api/vehicles/variants/{variantId}",
                        "not-a-valid-uuid")
        )
                .andExpect(status().isBadRequest());
    }
}