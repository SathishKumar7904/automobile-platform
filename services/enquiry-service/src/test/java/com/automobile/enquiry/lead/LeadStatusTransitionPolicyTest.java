package com.automobile.enquiry.lead;

import com.automobile.enquiry.exception.InvalidLeadStatusTransitionException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeadStatusTransitionPolicyTest {

    private LeadStatusTransitionPolicy policy;

    @BeforeEach
    void setUp() {
        policy = new LeadStatusTransitionPolicy();
    }

    @Test
    void shouldAllowNewToContacted() {
        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.NEW,
                        LeadStatus.CONTACTED));
    }

    @Test
    void shouldAllowContactedToInterested() {
        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.CONTACTED,
                        LeadStatus.INTERESTED));
    }

    @Test
    void shouldAllowInterestedToFollowUp() {
        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.INTERESTED,
                        LeadStatus.FOLLOW_UP));
    }

    @Test
    void shouldAllowFollowUpToConverted() {
        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.FOLLOW_UP,
                        LeadStatus.CONVERTED));
    }

    @Test
    void shouldAllowActiveLeadToClosed() {
        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.NEW,
                        LeadStatus.CLOSED));

        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.CONTACTED,
                        LeadStatus.CLOSED));

        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.INTERESTED,
                        LeadStatus.CLOSED));

        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.FOLLOW_UP,
                        LeadStatus.CLOSED));

        assertDoesNotThrow(() ->
                policy.validate(
                        LeadStatus.CONVERTED,
                        LeadStatus.CLOSED));
    }

    @Test
    void shouldRejectSkippingStatuses() {
        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.NEW,
                        LeadStatus.INTERESTED));

        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.NEW,
                        LeadStatus.CONVERTED));

        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.CONTACTED,
                        LeadStatus.CONVERTED));
    }

    @Test
    void shouldRejectBackwardTransitions() {
        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.INTERESTED,
                        LeadStatus.CONTACTED));

        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.FOLLOW_UP,
                        LeadStatus.INTERESTED));
    }

    @Test
    void shouldRejectChangingClosedLead() {
        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.CLOSED,
                        LeadStatus.CONTACTED));
    }

    @Test
    void shouldRejectSameStatus() {
        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> policy.validate(
                        LeadStatus.NEW,
                        LeadStatus.NEW));
    }
}
