package com.automobile.enquiry.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.automobile.enquiry.client.CustomerClient;
import com.automobile.enquiry.client.DealerClient;
import com.automobile.enquiry.client.VehicleClient;
import com.automobile.enquiry.dto.CreateEnquiryRequest;
import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.enquiry.Enquiry;
import com.automobile.enquiry.lead.Lead;
import com.automobile.enquiry.repository.EnquiryRepository;
import com.automobile.enquiry.repository.LeadRepository;

@Service
public class EnquiryService {
    private final EnquiryRepository enquiryRepository;
    private final LeadRepository leadRepository;
    private final CustomerClient customerClient;
    private final VehicleClient vehicleClient;
    private final DealerClient dealerClient;

    public EnquiryService(EnquiryRepository enquiryRepository,LeadRepository leadRepository,CustomerClient customerClient,VehicleClient vehicleClient,DealerClient dealerClient){
        this.enquiryRepository=enquiryRepository; this.leadRepository=leadRepository; this.customerClient=customerClient; this.vehicleClient=vehicleClient; this.dealerClient=dealerClient;
    }

    @Transactional
    public EnquiryCreatedResponse createEnquiry(CreateEnquiryRequest request){
        customerClient.validateCustomer(request.customerId());
        vehicleClient.validateVariant(request.variantId());
        dealerClient.validateDealer(request.dealerId());
        dealerClient.validateDealerCanHandleVariant(request.dealerId(),request.variantId());

        Enquiry enquiry=enquiryRepository.save(new Enquiry(UUID.randomUUID(),request.customerId(),request.variantId(),request.dealerId(),request.message()));
        Lead lead=leadRepository.save(new Lead(UUID.randomUUID(),enquiry.getId(),request.customerId(),request.variantId(),request.dealerId()));

        return new EnquiryCreatedResponse(enquiry.getId(),lead.getId(),enquiry.getCustomerId(),enquiry.getVariantId(),enquiry.getDealerId(),enquiry.getMessage(),lead.getStatus(),enquiry.getCreatedAt());
    }
}