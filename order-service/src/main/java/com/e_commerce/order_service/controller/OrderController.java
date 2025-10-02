package com.e_commerce.order_service.controller;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderRes getOrderByOrderId(@PathVariable long orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @GetMapping
    public List<OrderRes> getAllOrders(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return orderService.getAllOrders(pageNo, pageSize);
    }

    @GetMapping("/user/{userId}")
    public List<OrderRes> getOrdersByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return orderService.getOrdersByUserId(userId, pageNo, pageSize);
    }

    @PostMapping
    public OrderRes placeOrder(@RequestBody OrderReq orderReq,
                               @RequestParam String userId) {
        return orderService.placeOrder(orderReq, userId);
    }
}
