package com.e_commerce.order_service.service;


import com.e_commerce.order_service.dto.OrderItemReq;
import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.model.OrderItem;

import java.util.List;

public interface OrderService {
    OrderRes placeOrder(OrderReq orderReq, String userId);

    List<OrderRes> getMyOrders(String userId);

    OrderItem updateUserOrder(String userId, OrderItemReq orderItemReq);

}
