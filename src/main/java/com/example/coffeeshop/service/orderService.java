package com.example.coffeeshop.service;


import com.example.coffeeshop.domain.*;
import com.example.coffeeshop.dto.requestDto.OrderCoffeeDetailDto;
import com.example.coffeeshop.dto.requestDto.OrderCoffeeDto;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void orderCoffee(OrderCoffeeDto dto){
        Users user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        Order order = new Order();

        for (int i = 0; i < dto.getOrderCoffeeDetailDto().size(); i++) {
            OrderCoffeeDetailDto orderCoffeeDetailDto = dto.getOrderCoffeeDetailDto().get(i);
            Long coffeeId = orderCoffeeDetailDto.getCoffeeId();
            Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow();

            int quantity = orderCoffeeDetailDto.getQuantity();

            OrderDetail orderDetail = new OrderDetail(order,coffee,quantity);

            order.addOrderDetail(user,orderDetail);

        }
        user.usePoint(order.getTotalPrice());

        orderRepository.save(order);
    }
}
