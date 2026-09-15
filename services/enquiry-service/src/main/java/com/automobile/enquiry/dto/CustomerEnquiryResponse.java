package com.automobile.enquiry.dto;

import java.time.Instant;
import java.util.UUID;

import com.automobile.enquiry.lead.LeadStatus;

public record CustomerEnquiryResponse(
        UUID enquiryId,
        UUID leadId,
        UUID customerId,
        UUID variantId,
        UUID dealerId,
        String message,
        LeadStatus status,
        Instant submittedAt) {
}