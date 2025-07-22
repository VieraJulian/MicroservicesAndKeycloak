package com.microservices.inventory_service.service;

import com.microservices.inventory_service.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final IInventoryRepository iInventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return iInventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
