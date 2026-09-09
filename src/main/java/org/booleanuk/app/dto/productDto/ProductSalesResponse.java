package org.booleanuk.app.dto.productDto;

public record ProductSalesResponse(
        Long productId,
        String name,
        Double price,
        Long timesSold
) {
}
