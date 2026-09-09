package com.automobile.enquiry.controller;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.automobile.enquiry.dto.CreateEnquiryRequest;
import com.automobile.enquiry.dto.EnquiryCreatedResponse;
import com.automobile.enquiry.service.EnquiryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {
    private final EnquiryService enquiryService;
    public EnquiryController(EnquiryService enquiryService){this.enquiryService=enquiryService;}
    @PostMapping
    public ResponseEntity<EnquiryCreatedResponse> createEnquiry(@Valid @RequestBody CreateEnquiryRequest request){
        EnquiryCreatedResponse response=enquiryService.createEnquiry(request);
        return ResponseEntity.created(URI.create("/api/enquiries/"+response.enquiryId())).body(response);
    }
}