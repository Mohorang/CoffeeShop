package com.example.coffeeshop.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//CoffeeDto
public class CoffeeDto {
    private Long id;
    private String name;
    private int price;
}
