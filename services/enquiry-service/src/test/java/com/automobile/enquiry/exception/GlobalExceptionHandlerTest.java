
package com.automobile.enquiry.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.automobile.enquiry.lead.LeadStatus;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldMapLeadNotFoundTo404() {

        UUID leadId = UUID.randomUUID();

        Map<String, Object> response =
                handler.handleLeadNotFound(
                        new LeadNotFoundException(leadId));

        assertEquals(404, response.get("status"));
        assertEquals("Not Found", response.get("error"));
        assertNotNull(response.get("message"));
        assertNotNull(response.get("timestamp"));
    }

    @Test
    void shouldMapInvalidLeadStatusTransitionTo422() {

        Map<String, Object> response =
                handler.handleInvalidLeadStatusTransition(
                        new InvalidLeadStatusTransitionException(
                                LeadStatus.NEW,
                                LeadStatus.INTERESTED));

        assertEquals(422, response.get("status"));
        assertEquals(
                "Unprocessable Entity",
                response.get("error"));
        assertNotNull(response.get("message"));
        assertNotNull(response.get("timestamp"));
    }

    @Test
    void shouldMapInvalidDealerAssignmentTo422() {

        Map<String, Object> response =
                handler.handleInvalidDealer(
                        new InvalidDealerAssignmentException(
                                UUID.randomUUID(),
                                UUID.randomUUID()));

        assertEquals(422, response.get("status"));
        assertEquals(
                "Unprocessable Entity",
                response.get("error"));
        assertNotNull(response.get("message"));
        assertNotNull(response.get("timestamp"));
    }

    @Test
    void shouldMapDependencyUnavailableTo503() {

        Map<String, Object> response =
                handler.handleDependency(
                        new DependencyServiceUnavailableException(
                                "Vehicle service",
                                new RuntimeException("Connection refused")));

        assertEquals(503, response.get("status"));
        assertEquals(
                "Service Unavailable",
                response.get("error"));
        assertNotNull(response.get("message"));
        assertNotNull(response.get("timestamp"));
    }
}

