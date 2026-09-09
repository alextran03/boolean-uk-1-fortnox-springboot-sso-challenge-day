package org.booleanuk.app.repository;

import org.springframework.stereotype.Repository;
import org.booleanuk.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    
}
