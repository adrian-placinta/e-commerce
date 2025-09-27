package com.e_commerce.order_service.service;


import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;

import java.util.List;

public interface OrderService {
    OrderRes placeOrder(OrderReq orderReq);

    List<OrderRes> getMyOrders();

    List<OrderRes> updateMyOrders();

}
