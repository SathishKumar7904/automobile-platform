package com.automobile.vehicle.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.automobile.vehicle.variant.VehicleVariant;

public record VehicleVariantResponse(
        UUID id,
        UUID modelId,
        String name,
        String bodyType,
        String fuelType,
        String transmission,
        BigDecimal price
) {

    public static VehicleVariantResponse from(VehicleVariant variant) {
        return new VehicleVariantResponse(
                variant.getId(),
                variant.getModel().getId(),
                variant.getName(),
                variant.getBodyType(),
                variant.getFuelType(),
                variant.getTransmission(),
                variant.getPrice()
        );
    }
}