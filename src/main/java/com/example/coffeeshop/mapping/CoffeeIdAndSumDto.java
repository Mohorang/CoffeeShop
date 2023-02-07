package com.example.coffeeshop.mapping;

//repository에서 JPQL사용시 인터페이스를 만들어서 원하는 프로퍼티만 사용함.
public interface CoffeeIdAndSumDto {
    String getId();
    Integer getPrice();
}
