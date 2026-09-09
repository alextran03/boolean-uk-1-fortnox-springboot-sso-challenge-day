package org.booleanuk.app.repository;

import org.springframework.stereotype.Repository;
import org.booleanuk.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
