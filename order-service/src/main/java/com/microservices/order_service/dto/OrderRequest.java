package com.microservices.order_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderRequest(
        Long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity) {
}
