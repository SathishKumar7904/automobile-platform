
package com.automobile.enquiry.exception;

import java.util.UUID;

public class LeadNotFoundException extends RuntimeException {

    private final UUID leadId;

    public LeadNotFoundException(UUID leadId) {
        super("Lead not found: " + leadId);
        this.leadId = leadId;
    }

    public UUID getLeadId() {
        return leadId;
    }
}

