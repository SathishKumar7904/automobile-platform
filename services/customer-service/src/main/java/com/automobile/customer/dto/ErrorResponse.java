package com.automobile.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(
        name = "ErrorResponse",
        description = "Standard error response returned by the Customer Service API."
)
public record ErrorResponse(

        @Schema(
                description = "Short description of the error",
                example = "Customer Not Found"
        )
        String error,

        @Schema(
                description = "Detailed error message",
                example = "Customer not found with id: 550e8400-e29b-41d4-a716-446655440000"
        )
        String message,

        @Schema(
                description = "Field-level validation errors, when applicable",
                example = "{\"email\":\"Email must be valid\"}"
        )
        Map<String, String> fields
) {
}