package com.example.coffeeshop.controller;

import com.example.coffeeshop.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class orderController {

    private final orderService service;

    @PostMapping("/api/{coffeeId}/{userId}")
    public String coffeeOrder(@PathVariable Long userId,@PathVariable Long coffeeId){
        service.orderCoffee(userId,coffeeId);
        return "주문이 완료되었습니다.";
    }
}
