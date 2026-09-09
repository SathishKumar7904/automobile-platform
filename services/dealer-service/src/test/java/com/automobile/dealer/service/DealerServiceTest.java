package com.automobile.dealer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import com.automobile.dealer.dealer.Dealer;
import com.automobile.dealer.dealer.DealerRepository;
import com.automobile.dealer.inventory.DealerInventory;
import com.automobile.dealer.inventory.DealerInventoryRepository;

import org.junit.jupiter.api.Test;

class DealerServiceTest {
    @Test
    void shouldReturnActiveDealers() {
        DealerRepository dealerRepository = mock(DealerRepository.class);
        DealerInventoryRepository inventoryRepository = mock(DealerInventoryRepository.class);
        DealerService service = new DealerService(dealerRepository, inventoryRepository);

        when(dealerRepository.findAllByActiveTrueOrderByNameAsc()).thenReturn(List.of(
                new Dealer("BMW Chennai Motors", "Chennai", "Tamil Nadu", "Mount Road, Chennai", null, null)
        ));

        assertEquals(1, service.getActiveDealers().size());
        verify(dealerRepository).findAllByActiveTrueOrderByNameAsc();
    }

    @Test
    void shouldReturnDealersForVariant() {
        DealerRepository dealerRepository = mock(DealerRepository.class);
        DealerInventoryRepository inventoryRepository = mock(DealerInventoryRepository.class);
        DealerService service = new DealerService(dealerRepository, inventoryRepository);

        Dealer dealer = new Dealer("BMW Chennai Motors", "Chennai", "Tamil Nadu", "Mount Road, Chennai", null, null);
        UUID variantId = UUID.randomUUID();

        when(inventoryRepository.findAllByVariantIdAndAvailableTrueOrderByDealer_NameAsc(variantId))
                .thenReturn(List.of(new DealerInventory(dealer, variantId)));

        assertEquals(1, service.getDealersForVariant(variantId).size());
        verify(inventoryRepository).findAllByVariantIdAndAvailableTrueOrderByDealer_NameAsc(variantId);
    }
}