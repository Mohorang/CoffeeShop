package com.example.coffeeshop.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoffeeRank {
    private Long id;
    private String name;
    private int price;
}
