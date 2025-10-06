package com.e_commerce.order_service.service.impl;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.exception.OrderNotFoundException;
import com.e_commerce.order_service.messaging.OrderEventProducer;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import com.e_commerce.order_service.model.OrderStatus;
import com.e_commerce.order_service.model.factory.OrderCreatedEventFactory;
import com.e_commerce.order_service.model.factory.OrderFactory;
import com.e_commerce.order_service.repository.OrderRepository;
import com.e_commerce.order_service.service.OrderService;
import com.e_commerce.order_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    @Override
    public OrderRes getOrderByOrderId(final long orderId) {
        return orderRepository
                .findById(orderId)
                .map(OrderMapper::toOrderRes)
                .orElseThrow(() -> new OrderNotFoundException(String
                        .format("Order with id %s cannot be found", orderId)));
    }

    @Override
    public List<OrderRes> getAllOrders(final int pageNumber, final int pageSize) {
        return orderRepository
                .findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(OrderMapper::toOrderRes)
                .toList();
    }

    @Override
    public List<OrderRes> getOrdersByUserId(final String userId, final int pageNumber, final int pageSize) {
        return orderRepository.findAllOrdersByUserId(userId, PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(OrderMapper::toOrderRes)
                .toList();
    }

    @Transactional
    @Override
    public OrderRes placeOrder(final OrderReq orderReq, final String userId) {

        List<OrderItem> orderItems = orderReq
                .getOrderItems()
                .stream()
                .map(OrderMapper::toOrderItem)
                .toList();

        Order order = OrderFactory.createOrder(userId, orderItems, OrderStatus.PENDING);

        order.getOrderItems()
                .forEach(orderItem -> orderItem.setOrder(order));

        Order orderDb = orderRepository.save(order);

        orderEventProducer.publishOrderCreatedEvent(OrderCreatedEventFactory.fromOrder(orderDb));

        return OrderMapper.toOrderRes(orderDb);

    }


}
