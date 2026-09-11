package com.automobile.enquiry.client;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.ResourceAccessException;
import com.automobile.enquiry.exception.DependencyServiceUnavailableException;
import com.automobile.enquiry.exception.ReferencedResourceNotFoundException;

@Component
public class VehicleClient {
    private final RestClient client;
    public VehicleClient(RestClient.Builder builder, EnquiryServiceUrls urls) { this.client=builder.baseUrl(urls.getVehicleBaseUrl()).build(); }
    public void validateVariant(UUID variantId) {
        try {
            client.get().uri("/api/vehicles/variants/{id}",variantId).retrieve().body(VehicleVariantResponse.class);
        } catch (RestClientResponseException ex) {
            if(ex.getStatusCode().value()==404) throw new ReferencedResourceNotFoundException("Vehicle variant",variantId);
            throw new DependencyServiceUnavailableException("Vehicle Service",ex);
        } catch (ResourceAccessException ex) { throw new DependencyServiceUnavailableException("Vehicle Service",ex); }
    }
    private record VehicleVariantResponse(UUID id) {}
}