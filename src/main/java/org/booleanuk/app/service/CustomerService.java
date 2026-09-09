package org.booleanuk.app.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.repository.CustomerRepo;
import org.booleanuk.app.dto.customerDto.CustomerValueResponse;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return findOrThrow(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer data) {
        Customer customer = findOrThrow(id);
        customer.setName(data.getName());
        customer.setEmail(data.getEmail());
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepo.delete(findOrThrow(id));
    }

    public List<CustomerValueResponse> getCustomerValues() {
        return customerRepo.findCustomerValues();
    }

    private Customer findOrThrow(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
    }
}
