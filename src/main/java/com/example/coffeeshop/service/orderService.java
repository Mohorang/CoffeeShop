package com.example.coffeeshop.service;


import com.example.coffeeshop.model.Coffee;
import com.example.coffeeshop.model.Users;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class orderService {
    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void orderCoffee(Long userId, Long coffeeId){

        Coffee coffee= coffeeRepository.findById(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("에러 발생")
        );
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("에러 발생")
        );
        user.usePoint(coffee.getPrice());
        coffee.orderCountUp();
    }
}
