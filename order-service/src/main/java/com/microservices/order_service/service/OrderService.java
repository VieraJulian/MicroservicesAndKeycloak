package com.microservices.order_service.service;

import com.microservices.order_service.client.IInventoryClient;
import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.model.Order;
import com.microservices.order_service.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderRepository iOrderRepository;
    private final IInventoryClient iInventoryClient;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = iInventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .skuCode(orderRequest.skuCode())
                    .price(orderRequest.price())
                    .quantity(orderRequest.quantity())
                    .build();

            iOrderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
