package com.automobile.enquiry.dto;

import java.util.UUID;

import com.automobile.enquiry.lead.LeadStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateLeadStatusRequest(
        @NotNull LeadStatus status,
        UUID changedBy,
        @Size(max = 2000) String reason) {
}