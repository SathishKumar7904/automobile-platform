package com.automobile.enquiry.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automobile.enquiry.client.CustomerClient;
import com.automobile.enquiry.client.DealerClient;
import com.automobile.enquiry.client.VehicleClient;
import com.automobile.enquiry.dto.CreateEnquiryRequest;
import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.dto.LeadListItemResponse;
import com.automobile.enquiry.enquiry.Enquiry;
import com.automobile.enquiry.exception.LeadNotFoundException;
import com.automobile.enquiry.history.LeadStatusHistory;
import com.automobile.enquiry.history.LeadStatusHistoryRepository;
import com.automobile.enquiry.lead.Lead;
import com.automobile.enquiry.lead.LeadStatus;
import com.automobile.enquiry.lead.LeadStatusTransitionPolicy;
import com.automobile.enquiry.note.LeadNote;
import com.automobile.enquiry.note.LeadNoteRepository;
import com.automobile.enquiry.repository.EnquiryRepository;
import com.automobile.enquiry.repository.LeadRepository;
import com.automobile.enquiry.dto.CustomerEnquiryResponse;
@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final LeadRepository leadRepository;
    private final CustomerClient customerClient;
    private final VehicleClient vehicleClient;
    private final DealerClient dealerClient;
    private final LeadStatusHistoryRepository leadStatusHistoryRepository;
    private final LeadStatusTransitionPolicy leadStatusTransitionPolicy;
    private final LeadNoteRepository leadNoteRepository;

    public EnquiryService(
            EnquiryRepository enquiryRepository,
            LeadRepository leadRepository,
            CustomerClient customerClient,
            VehicleClient vehicleClient,
            DealerClient dealerClient,
            LeadStatusHistoryRepository leadStatusHistoryRepository,
            LeadStatusTransitionPolicy leadStatusTransitionPolicy,
            LeadNoteRepository leadNoteRepository) {

        this.enquiryRepository = enquiryRepository;
        this.leadRepository = leadRepository;
        this.customerClient = customerClient;
        this.vehicleClient = vehicleClient;
        this.dealerClient = dealerClient;
        this.leadStatusHistoryRepository = leadStatusHistoryRepository;
        this.leadStatusTransitionPolicy = leadStatusTransitionPolicy;
        this.leadNoteRepository = leadNoteRepository;
    }

    @Transactional
    public EnquiryCreatedResponse createEnquiry(CreateEnquiryRequest request) {

        customerClient.validateCustomer(request.customerId());
        vehicleClient.validateVariant(request.variantId());
        dealerClient.validateDealer(request.dealerId());
        dealerClient.validateDealerCanHandleVariant(
                request.dealerId(),
                request.variantId());

        Enquiry enquiry = enquiryRepository.save(
                new Enquiry(
                        UUID.randomUUID(),
                        request.customerId(),
                        request.variantId(),
                        request.dealerId(),
                        request.message()));

        Lead lead = leadRepository.save(
                new Lead(
                        UUID.randomUUID(),
                        enquiry.getId(),
                        request.customerId(),
                        request.variantId(),
                        request.dealerId()));

        return new EnquiryCreatedResponse(
                enquiry.getId(),
                lead.getId(),
                enquiry.getCustomerId(),
                enquiry.getVariantId(),
                enquiry.getDealerId(),
                enquiry.getMessage(),
                lead.getStatus(),
                enquiry.getCreatedAt());
    }

    @Transactional
    public Lead updateLeadStatus(
            UUID leadId,
            LeadStatus newStatus,
            UUID changedBy,
            String reason) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        LeadStatus previousStatus = lead.getStatus();

        leadStatusTransitionPolicy.validate(
                previousStatus,
                newStatus);

        lead.changeStatus(newStatus);

        LeadStatusHistory history = new LeadStatusHistory(
                UUID.randomUUID(),
                lead.getId(),
                previousStatus,
                newStatus,
                changedBy,
                Instant.now(),
                reason);

        leadStatusHistoryRepository.save(history);

        return leadRepository.save(lead);
    }

    @Transactional(readOnly = true)
    public List<LeadListItemResponse> getLeadsForDealer(UUID dealerId) {

        return leadRepository
                .findByDealerIdOrderByCreatedAtDesc(dealerId)
                .stream()
                .map(lead -> new LeadListItemResponse(
                lead.getId(),
                lead.getCustomerId(),
                lead.getVariantId(),
                lead.getStatus(),
                lead.getCreatedAt(),
                lead.getUpdatedAt(),
                lead.getDealerId()))
                .toList();
    }

    @Transactional(readOnly = true)
    public Lead getLead(UUID leadId) {

        return leadRepository.findById(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));
    }

    @Transactional
    public LeadNote addLeadNote(
            UUID leadId,
            String content,
            UUID createdBy) {

        leadRepository.findById(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        LeadNote note = new LeadNote(
                UUID.randomUUID(),
                leadId,
                content,
                createdBy,
                Instant.now());

        return leadNoteRepository.save(note);
    }

    @Transactional(readOnly = true)
    public List<LeadStatusHistory> getLeadHistory(UUID leadId) {

        leadRepository.findById(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        return leadStatusHistoryRepository
                .findByLeadIdOrderByChangedAtAsc(leadId);
    }
    @Transactional(readOnly = true)
public List<CustomerEnquiryResponse> getEnquiriesForCustomer(UUID customerId) {
    return enquiryRepository
            .findByCustomerIdOrderByCreatedAtDesc(customerId)
            .stream()
            .map(enquiry -> {
                Lead lead = leadRepository.findByEnquiryId(enquiry.getId())
                        .orElseThrow(() -> new LeadNotFoundException(
                                enquiry.getId()));

                return new CustomerEnquiryResponse(
                        enquiry.getId(),
                        lead.getId(),
                        enquiry.getCustomerId(),
                        enquiry.getVariantId(),
                        enquiry.getDealerId(),
                        enquiry.getMessage(),
                        lead.getStatus(),
                        enquiry.getCreatedAt());
            })
            .toList();
}

@Transactional(readOnly = true)
public CustomerEnquiryResponse getCustomerEnquiry(UUID enquiryId) {
    Enquiry enquiry = enquiryRepository.findById(enquiryId)
            .orElseThrow(() -> new RuntimeException(
                    "Enquiry not found: " + enquiryId));

    Lead lead = leadRepository.findByEnquiryId(enquiryId)
            .orElseThrow(() -> new LeadNotFoundException(enquiryId));

    return new CustomerEnquiryResponse(
            enquiry.getId(),
            lead.getId(),
            enquiry.getCustomerId(),
            enquiry.getVariantId(),
            enquiry.getDealerId(),
            enquiry.getMessage(),
            lead.getStatus(),
            enquiry.getCreatedAt());
}
}
