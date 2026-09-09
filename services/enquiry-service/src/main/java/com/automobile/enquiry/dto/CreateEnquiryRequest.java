package com.automobile.enquiry.dto;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record CreateEnquiryRequest(@NotNull UUID customerId,@NotNull UUID variantId,@NotNull UUID dealerId,@Size(max=2000) String message) {}