package com.automobile.vehicle.dto;

import java.util.UUID;

import com.automobile.vehicle.brand.Brand;

public record BrandResponse(
        UUID id,
        String name
) {

    public static BrandResponse from(Brand brand) {
        return new BrandResponse(
                brand.getId(),
                brand.getName()
        );
    }
}