package org.booleanuk.app.dto.productDto;

public record ProductResponse(
        Long id,
        String name,
        Double price
) {
}
