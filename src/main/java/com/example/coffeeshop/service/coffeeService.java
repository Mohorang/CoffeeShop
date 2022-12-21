package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.responseDto.CoffeeList;
import com.example.coffeeshop.dto.responseDto.CoffeeRank;
import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    //모든 커피 메뉴 반환
    public List<CoffeeList> getCoffeeList(){
        List<Coffee> coffees = coffeeRepository.findAll();
        List<CoffeeList> coffeeList = new ArrayList<>(coffees.size());
        for (int i = 0; i < coffees.size(); i++) {
            CoffeeList coffee = CoffeeList.builder()
                    .id(coffees.get(i).getId())
                    .name(coffees.get(i).getName())
                    .price(coffees.get(i).getPrice())
                    .build();
            coffeeList.add(coffee);
        }
        return coffeeList;
    }

    //인기메뉴 목록 조회
    public List<CoffeeRank> getCoffeeRank(){
        List<CoffeeRank> coffeeRanks = new ArrayList<>();
        List<Coffee> coffees = coffeeRepository.findAll(Sort.by(Sort.Direction.DESC,"orderCount"));
        for (int i = 0; i < coffees.size(); i++) {
            System.out.println(coffees.get(i).getName());
        }
        return coffeeRanks;
    }
}
