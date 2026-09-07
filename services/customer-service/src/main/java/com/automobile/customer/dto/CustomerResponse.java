package com.automobile.customer.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "CustomerResponse",
        description = "Customer information returned by the Customer Service API."
)
public record CustomerResponse(

        @Schema(
                description = "Unique customer identifier",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Customer first name",
                example = "John"
        )
        String firstName,

        @Schema(
                description = "Customer last name",
                example = "Doe"
        )
        String lastName,

        @Schema(
                description = "Customer email address",
                example = "john.doe@example.com"
        )
        String email,

        @Schema(
                description = "Customer phone number",
                example = "9876543210"
        )
        String phone,

        @Schema(
                description = "Customer creation timestamp",
                example = "2026-09-07T07:00:00Z"
        )
        OffsetDateTime createdAt,

        @Schema(
                description = "Customer last update timestamp",
                example = "2026-09-07T07:00:00Z"
        )
        OffsetDateTime updatedAt
) {
}