package com.automobile.dealer.inventory;

import java.time.Instant;
import java.util.UUID;

import com.automobile.dealer.dealer.Dealer;
import jakarta.persistence.*;

@Entity
@Table(name = "dealer_inventory",
       uniqueConstraints = @UniqueConstraint(
           name = "uk_dealer_inventory_dealer_variant",
           columnNames = {"dealer_id", "variant_id"}))
public class DealerInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealer_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_dealer_inventory_dealer"))
    private Dealer dealer;

    @Column(name = "variant_id", nullable = false)
    private UUID variantId;

    @Column(nullable = false)
    private boolean available = true;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected DealerInventory() {}

    public DealerInventory(Dealer dealer, UUID variantId) {
        this.dealer = dealer;
        this.variantId = variantId;
        this.available = true;
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }

    public UUID getId() { return id; }
    public Dealer getDealer() { return dealer; }
    public UUID getVariantId() { return variantId; }
    public boolean isAvailable() { return available; }
}