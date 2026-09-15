package com.automobile.enquiry.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.enquiry.lead.Lead;
import java.util.Optional;
public interface LeadRepository extends JpaRepository<Lead, UUID> {

    List<Lead> findByDealerIdOrderByCreatedAtDesc(UUID dealerId);
    Optional<Lead> findByEnquiryId(UUID enquiryId);
}