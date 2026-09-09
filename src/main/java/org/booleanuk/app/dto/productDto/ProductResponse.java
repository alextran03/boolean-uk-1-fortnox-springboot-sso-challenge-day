package org.booleanuk.app.dto.productDto;

import org.booleanuk.app.model.Product;

public record ProductResponse(
        Long id,
        String name,
        Double price
) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
