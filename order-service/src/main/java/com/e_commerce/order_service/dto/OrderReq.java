package com.e_commerce.order_service.dto;

import com.e_commerce.order_service.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReq {
    private List<OrderItemDto> orderItems;
}
