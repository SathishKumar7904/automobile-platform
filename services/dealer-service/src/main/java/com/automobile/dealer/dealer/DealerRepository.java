package com.automobile.dealer.dealer;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {
    List<Dealer> findAllByActiveTrueOrderByNameAsc();
}