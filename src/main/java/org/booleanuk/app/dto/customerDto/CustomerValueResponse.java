package org.booleanuk.app.dto.customerDto;

public record CustomerValueResponse(
        Long customerId,
        String name,
        double totalValue
) {
}
