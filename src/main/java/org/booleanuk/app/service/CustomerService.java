package org.booleanuk.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.model.Order;
import org.booleanuk.app.repository.CustomerRepo;
import org.booleanuk.app.dto.customerDto.CreateCustomerRequest;
import org.booleanuk.app.dto.customerDto.CustomerResponse;
import org.booleanuk.app.dto.customerDto.CustomerValueResponse;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepo.findAll().stream()
                .map(CustomerResponse::from)
                .toList();
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());

        return CustomerResponse.from(customerRepo.save(customer));
    }

    public CustomerResponse getCustomerById(Long id) {
        return CustomerResponse.from(findOrThrow(id));
    }

    public CustomerResponse updateCustomer(Long id, CreateCustomerRequest request) {
        Customer customer = findOrThrow(id);

        customer.setName(request.name());
        customer.setEmail(request.email());

        return CustomerResponse.from(customerRepo.save(customer));
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
