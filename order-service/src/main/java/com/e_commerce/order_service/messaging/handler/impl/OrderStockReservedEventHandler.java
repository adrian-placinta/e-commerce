package com.e_commerce.order_service.messaging.handler.impl;

import com.e_commerce.order_service.messaging.handler.OrderHandler;
import com.e_commerce.order_service.model.OrderStatus;
import com.e_commerce.order_service.service.OrderService;
import events.OrderStockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("orderStockReservedEventHandler")
@RequiredArgsConstructor
public class OrderStockReservedEventHandler implements OrderHandler<OrderStockReservedEvent> {
    private final OrderService orderService;

    @Override
    public void handle(OrderStockReservedEvent orderStockReservedEvent) {
        orderService.updateStatus(orderStockReservedEvent.orderId(), OrderStatus.COMPLETED);
    }
}
