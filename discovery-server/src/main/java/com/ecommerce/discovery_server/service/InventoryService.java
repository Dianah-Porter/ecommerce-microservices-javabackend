package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory save(Inventory inventory) {
        return repository.save(inventory);
    }

    public List<Inventory> findAll() {
        return repository.findAll();
    }

    public Inventory findByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public Inventory update(Long productId, Inventory inventory) {

        Inventory existing = findByProductId(productId);

        existing.setQuantity(inventory.getQuantity());

        return repository.save(existing);
    }

    public void delete(Long productId) {

        Inventory inventory = findByProductId(productId);

        repository.delete(inventory);

    }
}