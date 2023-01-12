package com.example.coffeeshop.service;

import com.example.coffeeshop.domain.OrderDetail;
import com.example.coffeeshop.dto.responseDto.CoffeeDto;
import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.mapping.CoffeeIdAndSumDto;
import com.example.coffeeshop.repository.CoffeeRepository;
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

    //모든 커피 메뉴 반환

    public List<CoffeeDto> getCoffeeList(){
        return coffeeRepository.findAll().stream().map(coffee -> CoffeeDto.builder()
                .id(coffee.getId())
                .name(coffee.getName())
                .price(coffee.getPrice())
                .build()).collect(Collectors.toList());

//        List<Coffee> coffees = coffeeRepository.findAll();
//        List<CoffeeDto> coffeeDtoList = new ArrayList<>();
//        for (int i = 0; i < coffees.size(); i++) {
//            CoffeeDto coffee = CoffeeDto.builder()
//                    .id(coffees.get(i).getId())
//                    .name(coffees.get(i).getName())
//                    .price(coffees.get(i).getPrice())
//                    .build();
//            coffeeDtoList.add(coffee);
//        }
//        return coffeeDtoList;
    }

    //일주일간 인기있었던 커피 반환
    public List<CoffeeIdAndSumDto> getPopularCoffeeList(){
        Pageable limit3 = PageRequest.of(0,3);
        List<CoffeeIdAndSumDto> result = orderRepository.findAllPopularCoffeeId(LocalDateTime.now().minusDays(7),limit3);

        for (CoffeeIdAndSumDto coffeeIdAndSumDto : result) {
            log.info("Coffee_id = {}, Total_Price_Sum = {}", coffeeIdAndSumDto.getId(), coffeeIdAndSumDto.getPrice());
        }

        return result;
    }

}
