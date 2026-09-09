package org.booleanuk.app.dto.customerDto;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
