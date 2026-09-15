package com.automobile.enquiry.history;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadStatusHistoryRepository
        extends JpaRepository<LeadStatusHistory, UUID> {

    List<LeadStatusHistory> findByLeadIdOrderByChangedAtAsc(UUID leadId);
}