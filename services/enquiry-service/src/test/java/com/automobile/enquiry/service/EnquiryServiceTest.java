
package com.automobile.enquiry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.automobile.enquiry.client.CustomerClient;
import com.automobile.enquiry.client.DealerClient;
import com.automobile.enquiry.client.VehicleClient;
import com.automobile.enquiry.dto.CreateEnquiryRequest;
import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.dto.LeadListItemResponse;
import com.automobile.enquiry.enquiry.Enquiry;
import com.automobile.enquiry.exception.InvalidLeadStatusTransitionException;
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

class EnquiryServiceTest {

    @Test
    void shouldCreateEnquiryAndLeadAfterAllReferencesAreValidated() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID customerId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();
        UUID dealerId = UUID.randomUUID();

        when(enquiryRepository.save(any(Enquiry.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(leadRepository.save(any(Lead.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EnquiryCreatedResponse response = service.createEnquiry(
                new CreateEnquiryRequest(
                        customerId,
                        variantId,
                        dealerId,
                        "I want the on-road price."));

        assertNotNull(response.enquiryId());
        assertNotNull(response.leadId());
        assertEquals(customerId, response.customerId());
        assertEquals(variantId, response.variantId());
        assertEquals(dealerId, response.dealerId());
        assertEquals(LeadStatus.NEW, response.leadStatus());

        var order = inOrder(
                customerClient,
                vehicleClient,
                dealerClient);

        order.verify(customerClient)
                .validateCustomer(customerId);

        order.verify(vehicleClient)
                .validateVariant(variantId);

        order.verify(dealerClient)
                .validateDealer(dealerId);

        order.verify(dealerClient)
                .validateDealerCanHandleVariant(
                        dealerId,
                        variantId);

        verify(enquiryRepository)
                .save(any(Enquiry.class));

        verify(leadRepository)
                .save(any(Lead.class));
    }

    @Test
    void shouldNotPersistWhenCustomerValidationFails() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID customerId = UUID.randomUUID();
        UUID variantId = UUID.randomUUID();
        UUID dealerId = UUID.randomUUID();

        doThrow(new RuntimeException("customer validation failed"))
                .when(customerClient)
                .validateCustomer(customerId);

        assertThrows(
                RuntimeException.class,
                () -> service.createEnquiry(
                        new CreateEnquiryRequest(
                                customerId,
                                variantId,
                                dealerId,
                                "Hello")));

        verifyNoInteractions(
                enquiryRepository,
                leadRepository,
                vehicleClient,
                dealerClient);
    }

    @Test
    void shouldUpdateLeadStatusAndCreateHistory() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID leadId = UUID.randomUUID();
        UUID changedBy = UUID.randomUUID();

        Lead lead = new Lead(
                leadId,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        when(leadRepository.findById(leadId))
                .thenReturn(Optional.of(lead));

        when(leadRepository.save(any(Lead.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(leadStatusHistoryRepository.save(
                any(LeadStatusHistory.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.updateLeadStatus(
                leadId,
                LeadStatus.CONTACTED,
                changedBy,
                "Customer contacted by phone.");

        verify(leadStatusTransitionPolicy)
                .validate(
                        LeadStatus.NEW,
                        LeadStatus.CONTACTED);

        assertEquals(
                LeadStatus.CONTACTED,
                lead.getStatus());

        verify(leadRepository)
                .save(lead);

        verify(leadStatusHistoryRepository)
                .save(argThat(history ->
                        history.getLeadId().equals(leadId)
                        && history.getPreviousStatus()
                                == LeadStatus.NEW
                        && history.getNewStatus()
                                == LeadStatus.CONTACTED
                        && history.getChangedBy().equals(changedBy)
                        && history.getReason().equals(
                                "Customer contacted by phone.")
                        && history.getChangedAt() != null));
    }

    @Test
    void shouldRejectInvalidLeadStatusTransition() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID leadId = UUID.randomUUID();

        Lead lead = new Lead(
                leadId,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        when(leadRepository.findById(leadId))
                .thenReturn(Optional.of(lead));

        doThrow(new InvalidLeadStatusTransitionException(
                LeadStatus.NEW,
                LeadStatus.INTERESTED))
                .when(leadStatusTransitionPolicy)
                .validate(
                        LeadStatus.NEW,
                        LeadStatus.INTERESTED);

        assertThrows(
                InvalidLeadStatusTransitionException.class,
                () -> service.updateLeadStatus(
                        leadId,
                        LeadStatus.INTERESTED,
                        UUID.randomUUID(),
                        "Invalid transition."));

        assertEquals(
                LeadStatus.NEW,
                lead.getStatus());

        verify(leadStatusTransitionPolicy)
                .validate(
                        LeadStatus.NEW,
                        LeadStatus.INTERESTED);

        verify(leadRepository, never())
                .save(any(Lead.class));

        verifyNoInteractions(
                leadStatusHistoryRepository);
    }

    @Test
    void shouldRejectStatusUpdateWhenLeadDoesNotExist() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID leadId = UUID.randomUUID();

        when(leadRepository.findById(leadId))
                .thenReturn(Optional.empty());

        assertThrows(
                LeadNotFoundException.class,
                () -> service.updateLeadStatus(
                        leadId,
                        LeadStatus.CONTACTED,
                        UUID.randomUUID(),
                        "Customer contacted."));

        verify(leadRepository)
                .findById(leadId);

        verify(leadRepository, never())
                .save(any(Lead.class));

        verifyNoInteractions(
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy);
    }

    @Test
    void shouldReturnOnlyLeadsBelongingToDealer() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID dealerId = UUID.randomUUID();

        Lead lead1 = new Lead(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                dealerId);

        Lead lead2 = new Lead(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                dealerId);

        when(leadRepository
                .findByDealerIdOrderByCreatedAtDesc(dealerId))
                .thenReturn(List.of(lead1, lead2));

        List<LeadListItemResponse> result =
                service.getLeadsForDealer(dealerId);

        assertEquals(2, result.size());

        assertEquals(
                dealerId,
                result.get(0).dealerId());

        assertEquals(
                dealerId,
                result.get(1).dealerId());

        verify(leadRepository)
                .findByDealerIdOrderByCreatedAtDesc(dealerId);

        verify(leadRepository, never())
                .findAll();
    }

    @Test
    void shouldCreateLeadNote() {

        EnquiryRepository enquiryRepository = mock(EnquiryRepository.class);
        LeadRepository leadRepository = mock(LeadRepository.class);
        CustomerClient customerClient = mock(CustomerClient.class);
        VehicleClient vehicleClient = mock(VehicleClient.class);
        DealerClient dealerClient = mock(DealerClient.class);
        LeadStatusHistoryRepository leadStatusHistoryRepository =
                mock(LeadStatusHistoryRepository.class);
        LeadStatusTransitionPolicy leadStatusTransitionPolicy =
                mock(LeadStatusTransitionPolicy.class);
        LeadNoteRepository leadNoteRepository =
                mock(LeadNoteRepository.class);

        EnquiryService service = new EnquiryService(
                enquiryRepository,
                leadRepository,
                customerClient,
                vehicleClient,
                dealerClient,
                leadStatusHistoryRepository,
                leadStatusTransitionPolicy,
                leadNoteRepository);

        UUID leadId = UUID.randomUUID();
        UUID createdBy = UUID.randomUUID();

        Lead lead = new Lead(
                leadId,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        when(leadRepository.findById(leadId))
                .thenReturn(Optional.of(lead));

        when(leadNoteRepository.save(any(LeadNote.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LeadNote result = service.addLeadNote(
                leadId,
                "Customer requested a test drive.",
                createdBy);

        assertNotNull(result);
        assertEquals(leadId, result.getLeadId());
        assertEquals(
                "Customer requested a test drive.",
                result.getContent());
        assertEquals(createdBy, result.getCreatedBy());

        verify(leadRepository)
                .findById(leadId);

        verify(leadNoteRepository)
                .save(any(LeadNote.class));
    }
}

