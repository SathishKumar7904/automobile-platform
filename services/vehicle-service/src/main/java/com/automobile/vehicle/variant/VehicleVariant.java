package com.automobile.vehicle.variant;

import com.automobile.vehicle.model.VehicleModel;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "vehicle_variants",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_vehicle_variants_model_name",
                        columnNames = {"model_id", "name"}
                )
        }
)
public class VehicleVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "model_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_vehicle_variants_model")
    )
    private VehicleModel model;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String bodyType;

    @Column(length = 50)
    private String fuelType;

    @Column(length = 50)
    private String transmission;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

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

    public VehicleVariant() {
    }

    public VehicleVariant(VehicleModel model, String name) {
        this.model = model;
        this.name = name;
        this.active = true;
    }

    public UUID getId() {
        return id;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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