package com.automobile.enquiry.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import com.automobile.enquiry.client.CustomerClient;
import com.automobile.enquiry.client.DealerClient;
import com.automobile.enquiry.client.VehicleClient;
import com.automobile.enquiry.dto.CreateEnquiryRequest;
import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.enquiry.Enquiry;
import com.automobile.enquiry.lead.Lead;
import com.automobile.enquiry.lead.LeadStatus;
import com.automobile.enquiry.repository.EnquiryRepository;
import com.automobile.enquiry.repository.LeadRepository;

class EnquiryServiceTest {
    @Test
    void shouldCreateEnquiryAndLeadAfterAllReferencesAreValidated(){
        EnquiryRepository enquiryRepository=mock(EnquiryRepository.class); LeadRepository leadRepository=mock(LeadRepository.class);
        CustomerClient customerClient=mock(CustomerClient.class); VehicleClient vehicleClient=mock(VehicleClient.class); DealerClient dealerClient=mock(DealerClient.class);
        EnquiryService service=new EnquiryService(enquiryRepository,leadRepository,customerClient,vehicleClient,dealerClient);
        UUID customerId=UUID.randomUUID(),variantId=UUID.randomUUID(),dealerId=UUID.randomUUID();
        when(enquiryRepository.save(any(Enquiry.class))).thenAnswer(inv->inv.getArgument(0));
        when(leadRepository.save(any(Lead.class))).thenAnswer(inv->inv.getArgument(0));
        EnquiryCreatedResponse response=service.createEnquiry(new CreateEnquiryRequest(customerId,variantId,dealerId,"I want the on-road price."));
        assertNotNull(response.enquiryId()); assertNotNull(response.leadId()); assertEquals(customerId,response.customerId()); assertEquals(variantId,response.variantId()); assertEquals(dealerId,response.dealerId()); assertEquals(LeadStatus.NEW,response.leadStatus());
        var order=inOrder(customerClient,vehicleClient,dealerClient);
        order.verify(customerClient).validateCustomer(customerId); order.verify(vehicleClient).validateVariant(variantId); order.verify(dealerClient).validateDealer(dealerId); order.verify(dealerClient).validateDealerCanHandleVariant(dealerId,variantId);
        verify(enquiryRepository).save(any(Enquiry.class)); verify(leadRepository).save(any(Lead.class));
    }

    @Test
    void shouldNotPersistWhenCustomerValidationFails(){
        EnquiryRepository enquiryRepository=mock(EnquiryRepository.class); LeadRepository leadRepository=mock(LeadRepository.class);
        CustomerClient customerClient=mock(CustomerClient.class); VehicleClient vehicleClient=mock(VehicleClient.class); DealerClient dealerClient=mock(DealerClient.class);
        EnquiryService service=new EnquiryService(enquiryRepository,leadRepository,customerClient,vehicleClient,dealerClient);
        UUID customerId=UUID.randomUUID(),variantId=UUID.randomUUID(),dealerId=UUID.randomUUID();
        doThrow(new RuntimeException("customer validation failed")).when(customerClient).validateCustomer(customerId);
        assertThrows(RuntimeException.class,()->service.createEnquiry(new CreateEnquiryRequest(customerId,variantId,dealerId,"Hello")));
        verifyNoInteractions(enquiryRepository,leadRepository,vehicleClient,dealerClient);
    }
}