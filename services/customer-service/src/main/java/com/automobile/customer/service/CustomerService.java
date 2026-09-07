package com.automobile.customer.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.automobile.customer.customer.Customer;
import com.automobile.customer.dto.CustomerResponse;
import com.automobile.customer.exception.CustomerNotFoundException;
import com.automobile.customer.repository.CustomerRepository;
import com.automobile.customer.exception.DuplicateEmailException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(
            String firstName,
            String lastName,
            String email,
            String phone
    ) {
        if (customerRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }

        OffsetDateTime now = OffsetDateTime.now();

        Customer customer = new Customer(
                UUID.randomUUID(),
                firstName,
                lastName,
                email,
                phone,
                now,
                now
        );

        Customer savedCustomer = customerRepository.save(customer);

        return toResponse(savedCustomer);

    }

    public CustomerResponse findCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return toResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CustomerResponse updateCustomer(
            UUID id,
            String firstName,
            String lastName,
            String email,
            String phone
    ) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        if (customerRepository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateEmailException(email);
        }

        customer.update(
                firstName,
                lastName,
                email,
                phone
        );

        Customer updatedCustomer = customerRepository.save(customer);

        return toResponse(updatedCustomer);
    }

    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customer);
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}
