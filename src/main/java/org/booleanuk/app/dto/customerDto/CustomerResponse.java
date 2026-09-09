package org.booleanuk.app.dto.customerDto;

import org.booleanuk.app.model.Customer;

public record CustomerResponse(Long id, String name, String email) {
      public static CustomerResponse from(Customer customer) {
          return new CustomerResponse(
                  customer.getId(),
                  customer.getName(),
                  customer.getEmail()
          );
      }
  }
