package com.automobile.enquiry.enquiry;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "enquiries")
public class Enquiry {
    @Id private UUID id;
    @Column(name = "customer_id", nullable = false) private UUID customerId;
    @Column(name = "variant_id", nullable = false) private UUID variantId;
    @Column(name = "dealer_id", nullable = false) private UUID dealerId;
    @Column(length = 2000) private String message;
    @Column(name = "created_at", nullable = false, updatable = false) private Instant createdAt;
    @Column(name = "updated_at", nullable = false) private Instant updatedAt;

    protected Enquiry() {}

    public Enquiry(UUID id, UUID customerId, UUID variantId, UUID dealerId, String message) {
        this.id = id;
        this.customerId = customerId;
        this.variantId = variantId;
        this.dealerId = dealerId;
        this.message = message;
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public UUID getVariantId() { return variantId; }
    public UUID getDealerId() { return dealerId; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}