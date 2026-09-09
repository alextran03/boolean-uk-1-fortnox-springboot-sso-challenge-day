package org.booleanuk.app.service;

import org.booleanuk.app.dto.orderDto.CreateOrderRequest;
import org.booleanuk.app.model.Customer;
import org.booleanuk.app.model.Order;
import org.booleanuk.app.model.Product;
import org.booleanuk.app.repository.CustomerRepo;
import org.booleanuk.app.repository.OrderRepo;
import org.booleanuk.app.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrdersByValue() {
        return orderRepo.findAllByOrderByTotalAmountDesc();
    }

    public Order getOrderById(Long id) {
        return findOrThrow(id);
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        Customer customer = findCustomer(request.customerId());
        List<Product> products = findProducts(request.productIds());

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(new HashSet<>(products));
        order.setTotalAmount(sumPrices(products));

        return orderRepo.save(order);
    }

    @Transactional
    public Order updateOrder(Long id, CreateOrderRequest request) {
        Order order = findOrThrow(id);
        Customer customer = findCustomer(request.customerId());
        List<Product> products = findProducts(request.productIds());

        order.setCustomer(customer);
        order.setProducts(new HashSet<>(products));
        order.setTotalAmount(sumPrices(products));

        return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.delete(findOrThrow(id));
    }

    // --- helpers ---

    private Order findOrThrow(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found with id: " + id));
    }

    private Customer findCustomer(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
    }

    private List<Product> findProducts(List<Long> ids) {
        List<Product> products = productRepo.findAllById(ids);
        if (products.size() != new HashSet<>(ids).size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more products do not exist");
        }
        return products;
    }

    private double sumPrices(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
