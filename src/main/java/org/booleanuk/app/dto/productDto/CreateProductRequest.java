package org.booleanuk.app.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.booleanuk.app.model.Product;

public record CreateProductRequest(
        @NotBlank String name,
        @NotNull @Positive Double price
) {
    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
