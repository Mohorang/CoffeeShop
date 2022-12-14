package com.example.coffeeshop.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCoffeeDetailDto {
    private Long coffeeId;
    private int quantity;

    public OrderCoffeeDetailDto(Long coffeeId, int quantity){
        this.coffeeId = coffeeId;
        this.quantity = quantity;
    }
}
