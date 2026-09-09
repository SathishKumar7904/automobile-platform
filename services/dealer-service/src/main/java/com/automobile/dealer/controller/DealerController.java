package com.automobile.dealer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.automobile.dealer.dto.DealerResponse;
import com.automobile.dealer.service.DealerService;

@RestController
@RequestMapping("/api")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping("/dealers")
    public ResponseEntity<List<DealerResponse>> getDealers() {
        return ResponseEntity.ok(dealerService.getActiveDealers());
    }

    @GetMapping("/dealers/{dealerId}")
    public ResponseEntity<DealerResponse> getDealer(@PathVariable UUID dealerId) {
        return ResponseEntity.ok(dealerService.getActiveDealer(dealerId));
    }

    @GetMapping("/vehicles/{variantId}/dealers")
    public ResponseEntity<List<DealerResponse>> getDealersForVehicle(
            @PathVariable UUID variantId) {
        return ResponseEntity.ok(dealerService.getDealersForVariant(variantId));
    }
}