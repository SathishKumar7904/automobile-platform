package com.automobile.enquiry.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.automobile.enquiry.lead.Lead;
public interface LeadRepository extends JpaRepository<Lead, UUID> {}