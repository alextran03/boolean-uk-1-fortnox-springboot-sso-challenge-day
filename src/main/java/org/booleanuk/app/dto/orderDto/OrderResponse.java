package org.booleanuk.app.dto.orderDto;

import org.booleanuk.app.model.Order;
import org.booleanuk.app.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        LocalDateTime createdAt,
        Long customerId,
        List<Long> productIds,
        Double totalAmount
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getCustomer().getId(),
                order.getProducts().stream().map(Product::getId).toList(),
                order.getTotalAmount()
        );
    }
}
