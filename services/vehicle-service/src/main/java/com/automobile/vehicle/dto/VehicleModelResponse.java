package com.automobile.vehicle.dto;

import java.util.UUID;

import com.automobile.vehicle.model.VehicleModel;

public record VehicleModelResponse(
        UUID id,
        UUID brandId,
        String name
) {

    public static VehicleModelResponse from(VehicleModel model) {
        return new VehicleModelResponse(
                model.getId(),
                model.getBrand().getId(),
                model.getName()
        );
    }
}