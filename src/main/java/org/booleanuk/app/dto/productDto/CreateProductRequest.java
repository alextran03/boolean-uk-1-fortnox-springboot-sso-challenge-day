package org.booleanuk.app.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateProductRequest(
        @NotBlank String name,
        @NotNull @Positive Double price
) {
}
