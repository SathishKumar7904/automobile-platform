package com.automobile.dealer.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automobile.dealer.dealer.Dealer;
import com.automobile.dealer.dealer.DealerRepository;
import com.automobile.dealer.dto.DealerResponse;
import com.automobile.dealer.exception.DealerNotFoundException;
import com.automobile.dealer.inventory.DealerInventory;
import com.automobile.dealer.inventory.DealerInventoryRepository;

@Service
@Transactional(readOnly = true)
public class DealerService {
    private final DealerRepository dealerRepository;
    private final DealerInventoryRepository inventoryRepository;

    public DealerService(DealerRepository dealerRepository, DealerInventoryRepository inventoryRepository) {
        this.dealerRepository = dealerRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<DealerResponse> getActiveDealers() {
        return dealerRepository.findAllByActiveTrueOrderByNameAsc()
                .stream().map(DealerResponse::from).toList();
    }

    public DealerResponse getActiveDealer(UUID dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .filter(Dealer::isActive)
                .orElseThrow(() -> new DealerNotFoundException(
                        "Active dealer not found: " + dealerId));
        return DealerResponse.from(dealer);
    }

    public List<DealerResponse> getDealersForVariant(UUID variantId) {
        return inventoryRepository.findAllByVariantIdAndAvailableTrueOrderByDealer_NameAsc(variantId)
                .stream()
                .filter(item -> item.getDealer().isActive())
                .map(item -> DealerResponse.from(item.getDealer()))
                .toList();
    }
}