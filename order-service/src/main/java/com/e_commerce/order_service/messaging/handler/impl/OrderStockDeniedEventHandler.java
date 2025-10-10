package com.e_commerce.order_service.messaging.handler.impl;

import com.e_commerce.order_service.messaging.handler.OrderHandler;
import com.e_commerce.order_service.model.OrderStatus;
import com.e_commerce.order_service.service.OrderService;
import events.OrderStockDeniedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("orderStockDeniedEventHandler")
@RequiredArgsConstructor
public class OrderStockDeniedEventHandler implements OrderHandler<OrderStockDeniedEvent> {
    private final OrderService orderService;

    @Override
    public void handle(OrderStockDeniedEvent orderStockDeniedEvent) {
        orderService.updateStatus(orderStockDeniedEvent.orderId(), OrderStatus.STOCK_DECLINED);
    }
}
