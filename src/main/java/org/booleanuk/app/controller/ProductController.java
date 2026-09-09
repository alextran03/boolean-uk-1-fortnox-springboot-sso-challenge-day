package org.booleanuk.app.controller;

import jakarta.validation.Valid;
import org.booleanuk.app.dto.productDto.CreateProductRequest;
import org.booleanuk.app.dto.productDto.ProductResponse;
import org.booleanuk.app.dto.productDto.ProductSalesResponse;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAllProducts().stream()
                .map(ProductResponse::from)
                .toList();
    }

    @GetMapping("/sales")
    public List<ProductSalesResponse> getSales() {
        return productService.getProductsWithSalesCount();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return ProductResponse.from(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
        Product created = productService.createProduct(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(created));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody CreateProductRequest request) {
        return ProductResponse.from(productService.updateProduct(id, request.toEntity()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
