package com.e_commerce.order_service.service;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.model.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderRes getOrderByOrderId(final long productId);

    List<OrderRes> getAllOrders(final int pageNo, final int pageSize);

    List<OrderRes> getOrdersByUserId(final String userId, final int pageNumber, final int pageSize);

    OrderRes placeOrder(final OrderReq orderReq, final String userId);

    void updateStatus(long orderId, OrderStatus orderStatus);
}
