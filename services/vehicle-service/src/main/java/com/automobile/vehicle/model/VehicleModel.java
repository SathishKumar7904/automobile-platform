package com.automobile.vehicle.model;

import com.automobile.vehicle.brand.Brand;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "vehicle_models",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_vehicle_models_brand_name",
                        columnNames = {"brand_id", "name"}
                )
        }
)
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "brand_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_vehicle_models_brand")
    )
    private Brand brand;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public VehicleModel() {
    }

    public VehicleModel(Brand brand, String name) {
        this.brand = brand;
        this.name = name;
        this.active = true;
    }

    public UUID getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}