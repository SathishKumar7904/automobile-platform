package com.automobile.dealer.dto;

import java.util.UUID;
import com.automobile.dealer.dealer.Dealer;

public record DealerResponse(
        UUID id,
        String name,
        String city,
        String state,
        String address,
        String phone,
        String email
) {
    public static DealerResponse from(Dealer dealer) {
        return new DealerResponse(
                dealer.getId(), dealer.getName(), dealer.getCity(), dealer.getState(),
                dealer.getAddress(), dealer.getPhone(), dealer.getEmail());
    }
}