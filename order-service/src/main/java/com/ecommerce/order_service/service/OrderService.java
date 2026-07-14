package com.ecommerce.order_service.service;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository repository,
                        RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Order create(Order order) {

        restTemplate.getForObject(
                "http://PRODUCT-SERVICE/products/" + order.getProductId(),
                Object.class);

        restTemplate.getForObject(
                "http://INVENTORY-SERVICE/inventory/" + order.getProductId(),
                Object.class);

        order.setStatus("CREATED");

        return repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    public Order update(Long id, Order updated) {

        Order order = getById(id);

        order.setQuantity(updated.getQuantity());

        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}