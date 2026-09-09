package com.automobile.enquiry.lead;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {
    @Id private UUID id;
    @Column(name="enquiry_id",nullable=false,unique=true) private UUID enquiryId;
    @Column(name="customer_id",nullable=false) private UUID customerId;
    @Column(name="variant_id",nullable=false) private UUID variantId;
    @Column(name="dealer_id",nullable=false) private UUID dealerId;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private LeadStatus status;
    @Column(name="created_at",nullable=false,updatable=false) private Instant createdAt;
    @Column(name="updated_at",nullable=false) private Instant updatedAt;
    protected Lead() {}
    public Lead(UUID id,UUID enquiryId,UUID customerId,UUID variantId,UUID dealerId){this.id=id;this.enquiryId=enquiryId;this.customerId=customerId;this.variantId=variantId;this.dealerId=dealerId;this.status=LeadStatus.NEW;Instant now=Instant.now();this.createdAt=now;this.updatedAt=now;}
    public UUID getId(){return id;} public UUID getEnquiryId(){return enquiryId;} public UUID getCustomerId(){return customerId;} public UUID getVariantId(){return variantId;} public UUID getDealerId(){return dealerId;} public LeadStatus getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}