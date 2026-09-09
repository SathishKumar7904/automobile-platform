package com.automobile.enquiry.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.services")
public class EnquiryServiceUrls {

    private String customerBaseUrl;
    private String vehicleBaseUrl;
    private String dealerBaseUrl;

    public String getCustomerBaseUrl() {
        return customerBaseUrl;
    }

    public void setCustomerBaseUrl(String customerBaseUrl) {
        this.customerBaseUrl = customerBaseUrl;
    }

    public String getVehicleBaseUrl() {
        return vehicleBaseUrl;
    }

    public void setVehicleBaseUrl(String vehicleBaseUrl) {
        this.vehicleBaseUrl = vehicleBaseUrl;
    }

    public String getDealerBaseUrl() {
        return dealerBaseUrl;
    }

    public void setDealerBaseUrl(String dealerBaseUrl) {
        this.dealerBaseUrl = dealerBaseUrl;
    }
}