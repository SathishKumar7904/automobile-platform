package com.automobile.enquiry.history;

import java.time.Instant;
import java.util.UUID;

import com.automobile.enquiry.lead.LeadStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "lead_status_history")
public class LeadStatusHistory {

    @Id
    private UUID id;

    @Column(name = "lead_id", nullable = false)
    private UUID leadId;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_status", length = 30)
    private LeadStatus previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false, length = 30)
    private LeadStatus newStatus;

    @Column(name = "changed_by")
    private UUID changedBy;

    @Column(name = "changed_at", nullable = false)
    private Instant changedAt;

    @Column(length = 2000)
    private String reason;

    protected LeadStatusHistory() {
    }

    public LeadStatusHistory(
            UUID id,
            UUID leadId,
            LeadStatus previousStatus,
            LeadStatus newStatus,
            UUID changedBy,
            Instant changedAt,
            String reason) {

        this.id = id;
        this.leadId = leadId;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
        this.reason = reason;
    }

    public UUID getId() {
        return id;
    }

    public UUID getLeadId() {
        return leadId;
    }

    public LeadStatus getPreviousStatus() {
        return previousStatus;
    }

    public LeadStatus getNewStatus() {
        return newStatus;
    }

    public UUID getChangedBy() {
        return changedBy;
    }

    public Instant getChangedAt() {
        return changedAt;
    }

    public String getReason() {
        return reason;
    }
}
