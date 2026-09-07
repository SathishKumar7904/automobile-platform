package com.automobile.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "CreateCustomerRequest",
        description = "Request payload used to create a new customer."
)
public record CreateCustomerRequest(

        @Schema(
                description = "Customer first name",
                example = "John"
        )
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @Schema(
                description = "Customer last name",
                example = "Doe"
        )
        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        @Schema(
                description = "Customer email address",
                example = "john.doe@example.com"
        )
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email,

        @Schema(
                description = "Customer phone number",
                example = "9876543210"
        )
        @Size(max = 30, message = "Phone must not exceed 30 characters")
        String phone
) {
}