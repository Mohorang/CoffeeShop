package com.example.coffeeshop.service;


import com.example.coffeeshop.domain.*;
import com.example.coffeeshop.dto.requestDto.OrderCoffeeDto;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public void orderCoffee(OrderCoffeeDto dto){
        Users user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        int totalPrice = getTotalPrice(dto);

        if(user.getPoint() < totalPrice){
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }

        Order order = new Order();

        for (int i = 0; i < dto.getCoffeeListDto().size(); i++) {
            Long coffeeId = dto.getCoffeeListDto().get(i).getCoffeeId();
            Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow();

            int quantity = dto.getCoffeeListDto().get(i).getQuantity();
            int price = coffee.getPrice();

            OrderDetail orderDetail = new OrderDetail(coffee,quantity,price);

            order.addOrder(user,orderDetail);
            orderRepository.save(order);
        }
        PointRecord pointRecord = new PointRecord();
        pointRecord.usePoint(user,totalPrice);


    }

    private int getTotalPrice(OrderCoffeeDto dto) {
        int totalPrice = 0;
        for (int i = 0; i < dto.getCoffeeListDto().size(); i++) {
            Long coffeeId = dto.getCoffeeListDto().get(i).getCoffeeId();
            Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow();

            int quantity = dto.getCoffeeListDto().get(i).getQuantity();
            int price = coffee.getPrice();

            totalPrice += price*quantity;
        }
        return totalPrice;
    }
}
