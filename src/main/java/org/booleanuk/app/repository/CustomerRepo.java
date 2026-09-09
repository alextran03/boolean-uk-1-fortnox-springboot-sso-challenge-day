 package org.booleanuk.app.repository;

import org.booleanuk.app.dto.customerDto.CustomerValueResponse;
import org.booleanuk.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("""
            SELECT new org.booleanuk.app.dto.customerDto.CustomerValueResponse(
                c.id, c.name, COALESCE(SUM(o.totalAmount), 0.0))
            FROM Customer c
            LEFT JOIN c.orders o
            GROUP BY c.id, c.name
            ORDER BY c.id
            """)
    List<CustomerValueResponse> findCustomerValues();
  }