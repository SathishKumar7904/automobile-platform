package com.automobile.enquiry.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.enquiry.dto.CreateLeadNoteRequest;
import com.automobile.enquiry.dto.LeadListItemResponse;
import com.automobile.enquiry.dto.UpdateLeadStatusRequest;
import com.automobile.enquiry.history.LeadStatusHistory;
import com.automobile.enquiry.lead.Lead;
import com.automobile.enquiry.note.LeadNote;
import com.automobile.enquiry.service.EnquiryService;

import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.automobile.enquiry.dto.UpdateLeadStatusRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

import com.automobile.enquiry.dto.CreateLeadNoteRequest;
import com.automobile.enquiry.note.LeadNote;
import com.automobile.enquiry.history.LeadStatusHistory;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final EnquiryService enquiryService;

    public LeadController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @GetMapping
    public ResponseEntity<List<LeadListItemResponse>> getLeads(
            @RequestParam UUID dealerId) {

        return ResponseEntity.ok(
                enquiryService.getLeadsForDealer(dealerId));
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<Lead> getLead(@PathVariable UUID leadId) {
        return ResponseEntity.ok(enquiryService.getLead(leadId));
    }

    @PatchMapping("/{leadId}/status")
    public ResponseEntity<Lead> updateLeadStatus(
            @PathVariable UUID leadId,
            @Valid @RequestBody UpdateLeadStatusRequest request) {

        Lead updatedLead = enquiryService.updateLeadStatus(
                leadId,
                request.status(),
                request.changedBy(),
                request.reason());

        return ResponseEntity.ok(updatedLead);
    }

    @PostMapping("/{leadId}/notes")
    public ResponseEntity<LeadNote> addLeadNote(
            @PathVariable UUID leadId,
            @Valid @RequestBody CreateLeadNoteRequest request) {

        LeadNote note = enquiryService.addLeadNote(
                leadId,
                request.content(),
                request.createdBy());

        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping("/{leadId}/history")
    public ResponseEntity<List<LeadStatusHistory>> getLeadHistory(
            @PathVariable UUID leadId) {

        return ResponseEntity.ok(
                enquiryService.getLeadHistory(leadId));
    }

}
