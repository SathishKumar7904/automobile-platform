package com.automobile.enquiry.exception;

import com.automobile.enquiry.lead.LeadStatus;

public class InvalidLeadStatusTransitionException extends RuntimeException {

    private final LeadStatus currentStatus;
    private final LeadStatus requestedStatus;

    public InvalidLeadStatusTransitionException(
            LeadStatus currentStatus,
            LeadStatus requestedStatus) {

        super("Invalid lead status transition: "
                + currentStatus + " -> " + requestedStatus);

        this.currentStatus = currentStatus;
        this.requestedStatus = requestedStatus;
    }

    public LeadStatus getCurrentStatus() {
        return currentStatus;
    }

    public LeadStatus getRequestedStatus() {
        return requestedStatus;
    }
}
