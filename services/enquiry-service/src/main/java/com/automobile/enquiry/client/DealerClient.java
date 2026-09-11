package com.automobile.enquiry.client;

import java.util.Arrays;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.ResourceAccessException;
import com.automobile.enquiry.exception.DependencyServiceUnavailableException;
import com.automobile.enquiry.exception.InvalidDealerAssignmentException;
import com.automobile.enquiry.exception.ReferencedResourceNotFoundException;

@Component
public class DealerClient {
    private final RestClient client;
    public DealerClient(RestClient.Builder builder, EnquiryServiceUrls urls) { this.client=builder.baseUrl(urls.getDealerBaseUrl()).build(); }
    public void validateDealer(UUID dealerId) {
        try {
            client.get().uri("/api/dealers/{id}",dealerId).retrieve().body(DealerResponse.class);
        } catch (RestClientResponseException ex) {
            if(ex.getStatusCode().value()==404) throw new ReferencedResourceNotFoundException("Dealer",dealerId);
            throw new DependencyServiceUnavailableException("Dealer Service",ex);
        } catch (ResourceAccessException ex) { throw new DependencyServiceUnavailableException("Dealer Service",ex); }
    }
    public void validateDealerCanHandleVariant(UUID dealerId, UUID variantId) {
        try {
            DealerResponse[] dealers=client.get().uri("/api/vehicles/{variantId}/dealers",variantId).retrieve().body(DealerResponse[].class);
            boolean available=dealers!=null && Arrays.stream(dealers).anyMatch(dealer -> dealerId.equals(dealer.id()));
            if(!available) throw new InvalidDealerAssignmentException(dealerId,variantId);
        } catch (RestClientResponseException ex) { throw new DependencyServiceUnavailableException("Dealer Service",ex); }
          catch (ResourceAccessException ex) { throw new DependencyServiceUnavailableException("Dealer Service",ex); }
    }
    private record DealerResponse(UUID id,String name) {}
}