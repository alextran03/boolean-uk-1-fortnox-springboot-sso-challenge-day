package org.booleanuk.app.repository;

import org.booleanuk.app.dto.productDto.ProductSalesResponse;
import org.booleanuk.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("""
            SELECT new org.booleanuk.app.dto.productDto.ProductSalesResponse(
                p.id, p.name, p.price, COUNT(o))
            FROM Product p
            LEFT JOIN p.orders o
            GROUP BY p.id, p.name, p.price
            ORDER BY COUNT(o) DESC, p.id
            """)
    List<ProductSalesResponse> findProductsWithSalesCount();
}
