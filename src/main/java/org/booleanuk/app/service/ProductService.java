package org.booleanuk.app.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.booleanuk.app.dto.productDto.ProductSalesResponse;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.repository.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return findOrThrow(id);
    }

    public Product createProduct(Product product) {
       return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product data) {
        Product product = findOrThrow(id);
        product.setName(data.getName());
        product.setPrice(data.getPrice());
        return productRepo.save(product);
    } 

    public void deleteProduct(Long id) {
        productRepo.delete(findOrThrow(id));
    }

    public List<ProductSalesResponse> getProductsWithSalesCount() {
        return productRepo.findProductsWithSalesCount();
    }

    private Product findOrThrow(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found with id: " + id));
    }

}
