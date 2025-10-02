package com.e_commerce.order_service.dto;

import com.e_commerce.order_service.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRes {
    private Long id;
    private List<OrderItemDto> orderItems;
}
