package com.e_commerce.order_service.util;

import com.e_commerce.order_service.dto.OrderItemDto;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;

import java.util.List;

public class OrderMapper {

    public static OrderRes toOrderRes(final Order order) {
        List<OrderItemDto> itemDtos = order.getOrderItems()
                .stream()
                .map(OrderMapper::toOrderItemDto)
                .toList();

        return OrderRes
                .builder()
                .id(order.getOrderId())
                .orderItems(itemDtos)
                .build();
    }

    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return OrderItemDto
                .builder()
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static OrderItem toOrderItem(OrderItemDto orderItemDto) {
        return OrderItem
                .builder()
                .productId(orderItemDto.getProductId())
                .quantity(orderItemDto.getQuantity())
                .build();
    }

}
