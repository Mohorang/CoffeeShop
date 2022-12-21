package com.example.coffeeshop.controller;

import com.example.coffeeshop.dto.requestDto.OrderCoffeeDto;
import com.example.coffeeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/api/orders")
    public String coffeeOrder(@RequestBody OrderCoffeeDto dto){
        service.orderCoffee(dto);
        return "주문이 완료되었습니다.";
    }
}
