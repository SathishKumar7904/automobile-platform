package com.automobile.enquiry.client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="app.services")
public record EnquiryServiceUrls(String customerBaseUrl,String vehicleBaseUrl,String dealerBaseUrl) {}