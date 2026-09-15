package com.automobile.enquiry.lead;

import org.springframework.stereotype.Component;

import com.automobile.enquiry.exception.InvalidLeadStatusTransitionException;

@Component
public class LeadStatusTransitionPolicy {

    public void validate(LeadStatus currentStatus, LeadStatus newStatus) {

        if (currentStatus == null || newStatus == null) {
            throw new InvalidLeadStatusTransitionException(
                    currentStatus,
                    newStatus);
        }

        if (currentStatus == newStatus) {
            throw new InvalidLeadStatusTransitionException(
                    currentStatus,
                    newStatus);
        }

        boolean allowed = switch (currentStatus) {
            case NEW -> newStatus == LeadStatus.CONTACTED
                    || newStatus == LeadStatus.CLOSED;

            case CONTACTED -> newStatus == LeadStatus.INTERESTED
                    || newStatus == LeadStatus.CLOSED;

            case INTERESTED -> newStatus == LeadStatus.FOLLOW_UP
                    || newStatus == LeadStatus.CLOSED;

            case FOLLOW_UP -> newStatus == LeadStatus.CONVERTED
                    || newStatus == LeadStatus.CLOSED;

            case CONVERTED -> newStatus == LeadStatus.CLOSED;

            case CLOSED -> false;
        };

        if (!allowed) {
            throw new InvalidLeadStatusTransitionException(
                    currentStatus,
                    newStatus);
        }
    }
}
