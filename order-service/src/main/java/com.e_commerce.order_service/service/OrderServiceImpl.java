package com.e_commerce.order_service.service;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.model.Order;
import com.e_commerce.order_service.repository.OrderRepository;
import com.e_commerce.order_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final JwtAuthenticationToken jwtAuthenticationToken;

    @Override
    public OrderRes placeOrder(OrderReq orderReq) {
        Order orderDb = OrderMapper.toEntity(orderReq, jwtAuthenticationToken.getToken().getSubject());
        orderDb
                .getOrderItems()
                .forEach(orderItem -> orderItem.setOrder(orderDb));

        return OrderMapper.toOrderRes(orderRepository.save(orderDb));
    }

    @Override
    public List<OrderRes> getMyOrders() {
        String userId = jwtAuthenticationToken.getToken().getSubject();

        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toOrderRes)
                .toList();
    }

    @Override
    public List<OrderRes> updateMyOrders() {
        return List.of();
    }
}
