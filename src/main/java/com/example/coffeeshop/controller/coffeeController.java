package com.example.coffeeshop.controller;


import com.example.coffeeshop.dto.responseDto.CoffeeDto;

import com.example.coffeeshop.dto.responseDto.CoffeeRank;
import com.example.coffeeshop.mapping.CoffeeIdAndSumDto;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.OrderRankRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffees")
@Slf4j
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;
    private final OrderRepository orderRepository;
    private final CoffeeService service;

    private final OrderRankRepository orderRankRepository;

    //커피 메뉴 목록 조회
    @GetMapping
    public List<CoffeeDto> coffeeList(){
        return service.getCoffeeList();
    }

    //인기메뉴 목록 조회
    @GetMapping("/popular")
    public List<CoffeeRank> coffeeRank(){

        return orderRankRepository.CoffeeRankIn7Days();
//        return service.getPopularCoffeeList();
    }

}
