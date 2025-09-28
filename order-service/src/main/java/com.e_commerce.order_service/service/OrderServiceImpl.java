package com.e_commerce.order_service.service;

import com.e_commerce.order_service.client.InventoryClient;
import com.e_commerce.order_service.dto.OrderItemReq;
import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.exception.InventoryUpdateException;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.model.OrderItem;
import com.e_commerce.order_service.repository.OrderRepository;
import com.e_commerce.order_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    @Value("${inventory.update.exception-message}")
    private String inventoryServiceMessageTemplate;

    @Override
    public OrderRes placeOrder(OrderReq orderReq, String userId) {
        Order orderDb = OrderMapper.toEntity(orderReq, userId);
        if (isInventoryUpdated(orderReq)) {
            orderDb.getOrderItems()
                    .forEach(orderItem -> orderItem.setOrder(orderDb));
            return OrderMapper.toOrderRes(orderRepository.save(orderDb));
        }
        throw new InventoryUpdateException(inventoryServiceMessageTemplate);
    }

    private boolean isInventoryUpdated(OrderReq orderReq) {
        return orderReq.orderItems()
                .stream()
                .map(orderItemReq -> inventoryClient
                        .updateStock(orderItemReq.productId(), -orderItemReq.quantity()))
                .allMatch(response -> response.getStatusCode().equals(HttpStatus.OK));
    }

    @Override
    public List<OrderRes> getMyOrders(String userId) {
        return orderRepository
                .findByUserId(userId)
                .stream()
                .map(OrderMapper::toOrderRes)
                .toList();
    }

    @Override
    public OrderItem updateUserOrder(String userId, OrderItemReq orderItemReq) {
        orderRepository
                .findOrderByUserIdAndOrderId(userId, orderItemReq.orderId())
                .orElseThrow(() -> new RuntimeException("Nu exista order asociat cu id-ul si sau user-ul"));


        //Then InventoryClient call

        //If ok -> update db -> if not don't update and rollback transaction

        //If db update fails, it should revert inventory changes.

    }


}
