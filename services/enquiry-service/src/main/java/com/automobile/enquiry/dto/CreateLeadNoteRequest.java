package com.automobile.enquiry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateLeadNoteRequest(
        @NotBlank
        @Size(max = 2000)
        String content,
        UUID createdBy) {
}