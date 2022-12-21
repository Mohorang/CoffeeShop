package com.example.coffeeshop.controller;

import com.example.coffeeshop.dto.responseDto.CoffeeList;
import com.example.coffeeshop.dto.responseDto.CoffeeRank;
import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffees")
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;
    private final CoffeeService service;

    @PostConstruct
    public void coffeeSetting(){
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(new Coffee("아메리카노",1500));
        coffees.add(new Coffee("바닐라라뗴",3300));
        coffees.add(new Coffee("딸기라떼",3500));
        coffees.add(new Coffee("레몬에이드",3000));
        for (int i = 0; i < coffees.size(); i++) {
            coffeeRepository.save(coffees.get(i));
        }
    }

    //커피 메뉴 목록 조회
    @GetMapping
    public List<CoffeeList> coffeeList(){
        List<CoffeeList> coffeeList = new ArrayList<>();
        coffeeList = service.getCoffeeList();

        return coffeeList;
    }
    //인기메뉴 목록 조회
    @GetMapping("/popular")
    public List<CoffeeRank> coffeeRank(){
        List<CoffeeRank> coffeeRanks = service.getCoffeeRank();
        return coffeeRanks;
    }

}
