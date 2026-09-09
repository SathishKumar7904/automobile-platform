package com.automobile.dealer.inventory;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerInventoryRepository extends JpaRepository<DealerInventory, UUID> {
    List<DealerInventory> findAllByVariantIdAndAvailableTrueOrderByDealer_NameAsc(UUID variantId);
}