package com.automobile.enquiry.note;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "lead_notes")
public class LeadNote {

    @Id
    private UUID id;

    @Column(name = "lead_id", nullable = false)
    private UUID leadId;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected LeadNote() {
    }

    public LeadNote(
            UUID id,
            UUID leadId,
            String content,
            UUID createdBy,
            Instant createdAt) {

        this.id = id;
        this.leadId = leadId;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getLeadId() {
        return leadId;
    }

    public String getContent() {
        return content;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
