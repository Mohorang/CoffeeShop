package com.example.coffeeshop.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCoffeeDto {
    private Long userId;
    private List<OrderCoffeeDetailDto> orderCoffeeDetailDto;
}
