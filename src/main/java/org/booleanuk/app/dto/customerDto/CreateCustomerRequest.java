package org.booleanuk.app.dto.customerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.booleanuk.app.model.Customer;

public record CreateCustomerRequest(
        @NotBlank String name,
        @NotBlank @Email String email
) {
    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        return customer;
    }
}
