package com.automobile.enquiry.client;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.automobile.enquiry.exception.DependencyServiceUnavailableException;
import com.automobile.enquiry.exception.ReferencedResourceNotFoundException;

@Component
public class CustomerClient {
    private final RestClient client;
    public CustomerClient(RestClient.Builder builder, EnquiryServiceUrls urls) { this.client=builder.baseUrl(urls.getCustomerBaseUrl()).build(); }
    public void validateCustomer(UUID customerId) {
        try {
            client.get().uri("/api/customers/{id}",customerId).retrieve().body(CustomerResponse.class);
        } catch (RestClientResponseException ex) {
            if(ex.getStatusCode().value()==404) throw new ReferencedResourceNotFoundException("Customer",customerId);
            throw new DependencyServiceUnavailableException("Customer Service",ex);
        } catch (ResourceAccessException ex) { throw new DependencyServiceUnavailableException("Customer Service",ex); }
    }
    private record CustomerResponse(UUID id) {}
}