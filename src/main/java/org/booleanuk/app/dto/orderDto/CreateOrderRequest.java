package org.booleanuk.app.dto.orderDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull Long customerId,
        @NotEmpty List<Long> productIds
) {
}
