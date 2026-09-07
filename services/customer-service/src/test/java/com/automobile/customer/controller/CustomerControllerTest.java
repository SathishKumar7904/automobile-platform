package com.automobile.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.automobile.customer.dto.CustomerResponse;
import com.automobile.customer.service.CustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {


@Autowired
private MockMvc mockMvc;

@MockitoBean
private CustomerService customerService;

private CustomerResponse createResponse(UUID id) {
    OffsetDateTime now = OffsetDateTime.now();

    return new CustomerResponse(
            id,
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            now,
            now
    );
}

@Test
void shouldGetAllCustomers() throws Exception {

    UUID id1 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();

    CustomerResponse customer1 = createResponse(id1);
    CustomerResponse customer2 = new CustomerResponse(
            id2,
            "Jane",
            "Smith",
            "jane@example.com",
            "9123456780",
            customer2CreatedAt(),
            customer2UpdatedAt()
    );

    when(customerService.getAllCustomers())
            .thenReturn(List.of(customer1, customer2));

    mockMvc.perform(get("/api/customers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(id1.toString()))
            .andExpect(jsonPath("$[0].firstName").value("John"))
            .andExpect(jsonPath("$[0].email").value("john@example.com"))
            .andExpect(jsonPath("$[1].firstName").value("Jane"))
            .andExpect(jsonPath("$[1].email").value("jane@example.com"));
}

@Test
void shouldGetCustomerById() throws Exception {

    UUID id = UUID.randomUUID();

    CustomerResponse response = createResponse(id);

    when(customerService.findCustomerById(id))
            .thenReturn(response);

    mockMvc.perform(
            get("/api/customers/{id}", id)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.phone").value("9876543210"));
}

@Test
void shouldCreateCustomer() throws Exception {

    UUID id = UUID.randomUUID();

    CustomerResponse response = createResponse(id);

    when(customerService.createCustomer(
            "John",
            "Doe",
            "john@example.com",
            "9876543210"
    )).thenReturn(response);

    String requestBody = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john@example.com",
                "phone": "9876543210"
            }
            """;

    mockMvc.perform(
            post("/api/customers")
                    .contentType("application/json")
                    .content(requestBody)
    )
            .andExpect(status().isCreated())
            .andExpect(header().string(
                    "Location",
                    "/api/customers/" + id
            ))
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.email").value("john@example.com"));
}

@Test
void shouldUpdateCustomer() throws Exception {

    UUID id = UUID.randomUUID();

    CustomerResponse response = new CustomerResponse(
            id,
            "Johnny",
            "Doe",
            "johnny@example.com",
            "9999999999",
            customer2CreatedAt(),
            customer2UpdatedAt()
    );

    when(customerService.updateCustomer(
            id,
            "Johnny",
            "Doe",
            "johnny@example.com",
            "9999999999"
    )).thenReturn(response);

    String requestBody = """
            {
                "firstName": "Johnny",
                "lastName": "Doe",
                "email": "johnny@example.com",
                "phone": "9999999999"
            }
            """;

    mockMvc.perform(
            put("/api/customers/{id}", id)
                    .contentType("application/json")
                    .content(requestBody)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.firstName").value("Johnny"))
            .andExpect(jsonPath("$.email").value("johnny@example.com"));
}

@Test
void shouldDeleteCustomer() throws Exception {

    UUID id = UUID.randomUUID();

    doNothing()
            .when(customerService)
            .deleteCustomer(id);

    mockMvc.perform(
            delete("/api/customers/{id}", id)
    )
            .andExpect(status().isNoContent());
}

private OffsetDateTime customer2CreatedAt() {
    return OffsetDateTime.now().minusDays(1);
}

private OffsetDateTime customer2UpdatedAt() {
    return OffsetDateTime.now();
}


}
