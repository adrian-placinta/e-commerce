package com.e_commerce.inventory_service.messaging.handler.impl;

import com.e_commerce.inventory_service.exceptions.InsufficientStockException;
import com.e_commerce.inventory_service.exceptions.StockNotFoundException;
import com.e_commerce.inventory_service.messaging.handler.OrderEventHandler;
import com.e_commerce.inventory_service.messaging.producer.OrderEventProducer;
import com.e_commerce.inventory_service.model.Stock;
import com.e_commerce.inventory_service.service.StockService;
import events.OrderCreatedEvent;
import events.OrderStockDeniedEvent;
import events.OrderStockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component("orderCreatedEventHandler")
@RequiredArgsConstructor
public class OrderCreatedEventHandler implements OrderEventHandler<OrderCreatedEvent> {
    private final OrderEventProducer<OrderStockReservedEvent> orderReservationEventProducer;
    private final OrderEventProducer<OrderStockDeniedEvent> orderStockDeniedEventProducer;
    private final StockService stockService;

    @Override
    @Transactional
    public void handle(final OrderCreatedEvent orderCreatedEvent) {
        try {
            List<Stock> updatedStocks = orderCreatedEvent.orderItems().stream()
                    .map(item -> stockService.subtractFromStock(item.quantity(), item.productId()))
                    .toList();

            stockService.updateAllStocks(updatedStocks);

            orderReservationEventProducer
                    .produce(new OrderStockReservedEvent(orderCreatedEvent.orderId(), Instant.now()));

        } catch (InsufficientStockException | StockNotFoundException e) {
            orderStockDeniedEventProducer
                    .produce(new OrderStockDeniedEvent(orderCreatedEvent.orderId(),
                            e.getMessage(),
                            Instant.now()));
        }
    }
}
