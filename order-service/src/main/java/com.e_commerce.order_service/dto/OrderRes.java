package com.e_commerce.order_service.dto;

import java.util.List;

public record OrderRes(Long id, List<OrderItemRes> orderItems) {
}