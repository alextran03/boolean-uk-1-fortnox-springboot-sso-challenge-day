package org.booleanuk.app.repository;

import org.booleanuk.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByTotalAmountDesc();
}
