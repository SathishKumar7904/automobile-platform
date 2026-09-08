package com.automobile.vehicle.controller;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void shouldReturnBrands() throws Exception {
        when(vehicleCatalogService.getActiveBrands())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/vehicles/brands"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

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
    void shouldReturnBadRequestForInvalidBrandUuid() throws Exception {
        mockMvc.perform(
                get("/api/vehicles/brands/{brandId}/models",
                        "not-a-valid-uuid")
        )
                .andExpect(status().isBadRequest());
    }
}
