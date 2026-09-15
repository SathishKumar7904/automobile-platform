package com.automobile.enquiry.dto;

import java.time.Instant;
import java.util.UUID;

import com.automobile.enquiry.lead.LeadStatus;

public record LeadListItemResponse(
        UUID leadId,
        UUID customerId,
        UUID variantId,
        LeadStatus status,
        Instant createdAt,
        Instant updatedAt,
        UUID dealerId) {
}