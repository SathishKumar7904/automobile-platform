package com.automobile.customer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.automobile.customer.customer.Customer;

@SpringBootTest
class CustomerRepositoryIntegrationTest {


@Autowired
private CustomerRepository customerRepository;

@Test
void shouldSaveAndFindCustomerById() {

    String email = "john." + UUID.randomUUID() + "@integration.test";

    Customer customer = createCustomer(
            "John",
            "Doe",
            email,
            "9876543210"
    );

    Customer savedCustomer = customerRepository.save(customer);

    Customer foundCustomer = customerRepository
            .findById(savedCustomer.getId())
            .orElse(null);

    assertThat(foundCustomer).isNotNull();

    assertThat(foundCustomer.getId())
            .isEqualTo(savedCustomer.getId());

    assertThat(foundCustomer.getFirstName())
            .isEqualTo("John");

    assertThat(foundCustomer.getLastName())
            .isEqualTo("Doe");

    assertThat(foundCustomer.getEmail())
            .isEqualTo(email);

    assertThat(foundCustomer.getPhone())
            .isEqualTo("9876543210");
}

@Test
void shouldFindAllCustomers() {

    String email = "alice." + UUID.randomUUID() + "@integration.test";

    Customer customer = createCustomer(
            "Alice",
            "Smith",
            email,
            "9876543211"
    );

    customerRepository.save(customer);

    List<Customer> customers = customerRepository.findAll();

    assertThat(customers).isNotEmpty();

    assertThat(customers)
            .anyMatch(c -> c.getEmail().equals(email));
}

@Test
void shouldUpdateCustomer() {

    String email = "robert." + UUID.randomUUID() + "@integration.test";
    String updatedEmail =
            "robert.updated." + UUID.randomUUID() + "@integration.test";

    Customer customer = createCustomer(
            "Robert",
            "Brown",
            email,
            "9876543212"
    );

    Customer savedCustomer = customerRepository.save(customer);

    savedCustomer.update(
            "Robert",
            "Johnson",
            updatedEmail,
            "9876543213"
    );

    Customer updatedCustomer =
            customerRepository.save(savedCustomer);

    Customer foundCustomer = customerRepository
            .findById(updatedCustomer.getId())
            .orElse(null);

    assertThat(foundCustomer).isNotNull();

    assertThat(foundCustomer.getFirstName())
            .isEqualTo("Robert");

    assertThat(foundCustomer.getLastName())
            .isEqualTo("Johnson");

    assertThat(foundCustomer.getEmail())
            .isEqualTo(updatedEmail);

    assertThat(foundCustomer.getPhone())
            .isEqualTo("9876543213");
}

@Test
void shouldDeleteCustomer() {

    String email = "delete." + UUID.randomUUID() + "@integration.test";

    Customer customer = createCustomer(
            "Delete",
            "Test",
            email,
            "9876543214"
    );

    Customer savedCustomer = customerRepository.save(customer);

    UUID customerId = savedCustomer.getId();

    assertThat(customerRepository.findById(customerId))
            .isPresent();

    customerRepository.delete(savedCustomer);

    assertThat(customerRepository.findById(customerId))
            .isEmpty();
}

private Customer createCustomer(
        String firstName,
        String lastName,
        String email,
        String phone
) {
    OffsetDateTime now = OffsetDateTime.now();

    return new Customer(
            UUID.randomUUID(),
            firstName,
            lastName,
            email,
            phone,
            now,
            now
    );
}


}
