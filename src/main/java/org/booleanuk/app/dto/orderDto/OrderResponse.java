package org.booleanuk.app.dto.orderDto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        LocalDateTime createdAt,
        Long customerId,
        List<Long> productIds,
        Double totalAmount
) {
}
