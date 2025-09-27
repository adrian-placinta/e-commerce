package com.e_commerce.order_service.dto;

import java.util.List;

public record OrderReq(List<OrderItemReq> orderItems) {
}