package com.e_commerce.order_service.util;

import com.e_commerce.order_service.dto.OrderItemReq;
import com.e_commerce.order_service.dto.OrderItemRes;
import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;

import java.util.List;

public class OrderMapper {

    public static Order toEntity(OrderReq orderReq, String subject) {
        return Order
                .builder()
                .userId(subject)
                .orderItems(toOrderItemList(orderReq.orderItems()))
                .build();
    }

    public static OrderRes toOrderRes(Order order) {
        return new OrderRes(order.getId(), toOrderItemResList(order.getOrderItems()));
    }

    private static OrderItem toOrderItem(OrderItemReq orderItemReq) {
        return OrderItem
                .builder()
                .productId(orderItemReq.productId())
                .quantity(orderItemReq.quantity())
                .build();
    }

    private static List<OrderItem> toOrderItemList(List<OrderItemReq> orderItemReqs) {
        return orderItemReqs
                .stream()
                .map(OrderMapper::toOrderItem)
                .toList();
    }

    public static OrderItemRes toOrderItemRes(OrderItem order) {
        return new OrderItemRes(order.getId(),
                order.getProductId(),
                order.getProductId(),
                order.getQuantity());
    }

    private static List<OrderItemRes> toOrderItemResList(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderMapper::toOrderItemRes).toList();
    }

}
