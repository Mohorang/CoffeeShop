package com.example.coffeeshop.service;

import com.example.coffeeshop.domain.OrderDetail;
import com.example.coffeeshop.dto.responseDto.CoffeeDto;
import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.dto.responseDto.CoffeeRank;
import com.example.coffeeshop.mapping.CoffeeIdAndSumDto;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.OrderRankRepository;
import com.example.coffeeshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final OrderRepository orderRepository;
    private final OrderRankRepository orderRankRepository;

    //모든 커피 메뉴 반환

    public List<CoffeeDto> getCoffeeList(){
        return coffeeRepository.findAll().stream().map(coffee -> CoffeeDto.builder()
                .id(coffee.getId())
                .name(coffee.getName())
                .price(coffee.getPrice())
                .build()).collect(Collectors.toList());

    }

    //일주일간 인기있었던 커피 반환
    public List<CoffeeRank> getPopularCoffeeList(){
        List<CoffeeRank> result = orderRankRepository.CoffeeRankIn7Days();
        int i =0;
        for (CoffeeRank coffeeRank : result) {
            log.info("coffeeRank No.{} = {}, price = {}",++i, coffeeRank.getId(),coffeeRank.getPrice());
        }

        return result;
    }

}
