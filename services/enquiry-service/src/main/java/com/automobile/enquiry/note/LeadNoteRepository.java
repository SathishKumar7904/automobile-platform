package com.automobile.enquiry.note;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadNoteRepository
        extends JpaRepository<LeadNote, UUID> {

    List<LeadNote> findByLeadIdOrderByCreatedAtAsc(UUID leadId);
}
