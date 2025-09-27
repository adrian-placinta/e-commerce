package com.e_commerce.order_service.controller;

import com.e_commerce.order_service.dto.OrderReq;
import com.e_commerce.order_service.dto.OrderRes;
import com.e_commerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/")
    ResponseEntity<OrderRes> placeOrder(@RequestBody OrderReq orderReq,
                                        @AuthenticationPrincipal JwtAuthenticationToken jwtAuthenticationToken) {
        final String userId = jwtAuthenticationToken.getToken().getSubject();
        return ResponseEntity.ok(orderService.placeOrder(orderReq, userId));
    }

    @PutMapping("/{id}")
    ResponseEntity<OrderRes> updateOrder(@RequestBody OrderReq orderReq,
                                        @AuthenticationPrincipal JwtAuthenticationToken jwtAuthenticationToken) {
        final String userId = jwtAuthenticationToken.getToken().getSubject();
        return ResponseEntity.ok(orderService.placeOrder(orderReq, userId));
    }
}
