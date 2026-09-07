package com.automobile.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.automobile.customer.customer.Customer;
import com.automobile.customer.dto.CustomerResponse;
import com.automobile.customer.exception.CustomerNotFoundException;
import com.automobile.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


@Mock
private CustomerRepository customerRepository;

private CustomerService customerService;

@BeforeEach
void setUp() {
    customerService = new CustomerService(customerRepository);
}

@Test
void shouldCreateCustomer() {

    UUID id = UUID.randomUUID();
    OffsetDateTime now = OffsetDateTime.now();

    Customer savedCustomer = new Customer(
            id,
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            now,
            now
    );

    when(customerRepository.save(org.mockito.ArgumentMatchers.any(Customer.class)))
            .thenReturn(savedCustomer);

    CustomerResponse response = customerService.createCustomer(
            "John",
            "Doe",
            "john@example.com",
            "9876543210"
    );

    assertNotNull(response);
    assertEquals(id, response.id());
    assertEquals("John", response.firstName());
    assertEquals("Doe", response.lastName());
    assertEquals("john@example.com", response.email());
    assertEquals("9876543210", response.phone());

    verify(customerRepository).save(
            org.mockito.ArgumentMatchers.any(Customer.class)
    );
}

@Test
void shouldFindCustomerById() {

    UUID id = UUID.randomUUID();
    OffsetDateTime now = OffsetDateTime.now();

    Customer customer = new Customer(
            id,
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            now,
            now
    );

    when(customerRepository.findById(id))
            .thenReturn(Optional.of(customer));

    CustomerResponse response =
            customerService.findCustomerById(id);

    assertNotNull(response);
    assertEquals(id, response.id());
    assertEquals("John", response.firstName());
    assertEquals("Doe", response.lastName());
    assertEquals("john@example.com", response.email());
}

@Test
void shouldThrowExceptionWhenCustomerNotFoundById() {

    UUID id = UUID.randomUUID();

    when(customerRepository.findById(id))
            .thenReturn(Optional.empty());

    assertThrows(
            CustomerNotFoundException.class,
            () -> customerService.findCustomerById(id)
    );
}

@Test
void shouldGetAllCustomers() {

    OffsetDateTime now = OffsetDateTime.now();

    Customer customer1 = new Customer(
            UUID.randomUUID(),
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            now,
            now
    );

    Customer customer2 = new Customer(
            UUID.randomUUID(),
            "Jane",
            "Smith",
            "jane@example.com",
            "9123456780",
            now,
            now
    );

    when(customerRepository.findAll())
            .thenReturn(List.of(customer1, customer2));

    List<CustomerResponse> responses =
            customerService.getAllCustomers();

    assertNotNull(responses);
    assertEquals(2, responses.size());
    assertEquals("John", responses.get(0).firstName());
    assertEquals("Jane", responses.get(1).firstName());
}

@Test
void shouldUpdateCustomer() {

    UUID id = UUID.randomUUID();
    OffsetDateTime createdAt = OffsetDateTime.now().minusDays(1);
    OffsetDateTime updatedAt = OffsetDateTime.now();

    Customer customer = new Customer(
            id,
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            createdAt,
            updatedAt
    );

    when(customerRepository.findById(id))
            .thenReturn(Optional.of(customer));

    when(customerRepository.save(customer))
            .thenReturn(customer);

    CustomerResponse response =
            customerService.updateCustomer(
                    id,
                    "Johnny",
                    "Doe",
                    "johnny@example.com",
                    "9999999999"
            );

    assertNotNull(response);
    assertEquals(id, response.id());
    assertEquals("Johnny", response.firstName());
    assertEquals("Doe", response.lastName());
    assertEquals("johnny@example.com", response.email());
    assertEquals("9999999999", response.phone());

    verify(customerRepository).save(customer);
}

@Test
void shouldThrowExceptionWhenUpdatingNonExistingCustomer() {

    UUID id = UUID.randomUUID();

    when(customerRepository.findById(id))
            .thenReturn(Optional.empty());

    assertThrows(
            CustomerNotFoundException.class,
            () -> customerService.updateCustomer(
                    id,
                    "John",
                    "Doe",
                    "john@example.com",
                    "9876543210"
            )
    );
}

@Test
void shouldDeleteCustomer() {

    UUID id = UUID.randomUUID();
    OffsetDateTime now = OffsetDateTime.now();

    Customer customer = new Customer(
            id,
            "John",
            "Doe",
            "john@example.com",
            "9876543210",
            now,
            now
    );

    when(customerRepository.findById(id))
            .thenReturn(Optional.of(customer));

    customerService.deleteCustomer(id);

    verify(customerRepository).delete(customer);
}

@Test
void shouldThrowExceptionWhenDeletingNonExistingCustomer() {

    UUID id = UUID.randomUUID();

    when(customerRepository.findById(id))
            .thenReturn(Optional.empty());

    assertThrows(
            CustomerNotFoundException.class,
            () -> customerService.deleteCustomer(id)
    );
}


}
