package com.automobile.enquiry.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.lead.LeadStatus;
import com.automobile.enquiry.service.EnquiryService;

@WebMvcTest(EnquiryController.class)
class EnquiryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnquiryService enquiryService;

    @Test
    void shouldCreateEnquiryAndReturnCreatedResponse() throws Exception {
        UUID customerId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();
        UUID dealerId = UUID.randomUUID();
        UUID enquiryId = UUID.randomUUID();
        UUID leadId = UUID.randomUUID();

        when(enquiryService.createEnquiry(org.mockito.ArgumentMatchers.any()))
                .thenReturn(new EnquiryCreatedResponse(
                        enquiryId,
                        leadId,
                        customerId,
                        variantId,
                        dealerId,
                        "I want the on-road price.",
                        LeadStatus.NEW,
                        Instant.parse("2026-09-09T12:00:00Z")
                ));

        String request = """
                {
                  "customerId": "%s",
                  "variantId": "%s",
                  "dealerId": "%s",
                  "message": "I want the on-road price."
                }
                """.formatted(customerId, variantId, dealerId);

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/enquiries/" + enquiryId))
                .andExpect(jsonPath("$.enquiryId").value(enquiryId.toString()))
                .andExpect(jsonPath("$.leadId").value(leadId.toString()))
                .andExpect(jsonPath("$.customerId").value(customerId.toString()))
                .andExpect(jsonPath("$.variantId").value(variantId.toString()))
                .andExpect(jsonPath("$.dealerId").value(dealerId.toString()))
                .andExpect(jsonPath("$.leadStatus").value("NEW"));

        verify(enquiryService).createEnquiry(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void shouldRejectRequestWhenRequiredReferenceIsMissing() throws Exception {
        String request = """
                {
                  "customerId": null,
                  "variantId": "%s",
                  "dealerId": "%s",
                  "message": "I want the on-road price."
                }
                """.formatted(UUID.randomUUID(), UUID.randomUUID());

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.fields.customerId").exists());

        verifyNoInteractions(enquiryService);
    }

    @Test
    void shouldRejectMessageLongerThanTwoThousandCharacters() throws Exception {
        String message = "x".repeat(2001);
        String request = """
                {
                  "customerId": "%s",
                  "variantId": "%s",
                  "dealerId": "%s",
                  "message": "%s"
                }
                """.formatted(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), message);

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fields.message").exists());

        verifyNoInteractions(enquiryService);
    }
}
