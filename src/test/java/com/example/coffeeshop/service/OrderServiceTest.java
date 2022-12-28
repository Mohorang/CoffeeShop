package com.example.coffeeshop.service;

import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.domain.Users;
import com.example.coffeeshop.dto.requestDto.CoffeeListDto;
import com.example.coffeeshop.dto.requestDto.OrderCoffeeDto;
import com.example.coffeeshop.repository.CoffeeRepository;
import com.example.coffeeshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired CoffeeRepository coffeeRepository;
    @Autowired OrderService orderService;
    @Autowired UserRepository userRepository;

//  coffees.add(new Coffee("아메리카노",1500));
//  coffees.add(new Coffee("바닐라라뗴",3300));
//  coffees.add(new Coffee("딸기라떼",3500));
//  coffees.add(new Coffee("레몬에이드",3000));

private int getTotalPrice(OrderCoffeeDto dto) {
    int totalPrice = 0;
    for (int i = 0; i < dto.getCoffeeListDto().size(); i++) {
        Long coffeeId = dto.getCoffeeListDto().get(i).getCoffeeId();
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow();

        int quantity = dto.getCoffeeListDto().get(i).getQuantity();
        int price = coffee.getPrice();

        totalPrice += price*quantity;
    }
    return totalPrice;
}
    @BeforeEach
    public OrderCoffeeDto createOrderCoffeeDto(){

        OrderCoffeeDto orderCoffeeDto = new OrderCoffeeDto();
        List<CoffeeListDto> coffeeListDto = new ArrayList<>();
        coffeeListDto.add(new CoffeeListDto(1L,2));
        coffeeListDto.add(new CoffeeListDto(2L,3));
        orderCoffeeDto.setCoffeeListDto(coffeeListDto);
        orderCoffeeDto.setUserId(1L);

        return orderCoffeeDto;
    }

    //커피정보 조회 하는 테스트는 뭘 체크해야하지??
    @Test
    public void 커피정보조회_테스트() throws Exception{
        //given
        List<Coffee> testCoffees = new ArrayList<>();

        //when
        testCoffees = coffeeRepository.findAll();

        //then
        assertEquals("아메리카노",testCoffees.get(0).getName());
        assertEquals("바닐라라뗴",testCoffees.get(1).getName());
        assertEquals("딸기라떼",testCoffees.get(2).getName());
        assertEquals("레몬에이드",testCoffees.get(3).getName());
    }

    /**
     * 사용자 id , 커피 id를 입력받아 주문 및 결제
     * 결제는 포인트로만 가능
     */
    @Test
    public void 커피_주문_테스트() throws Exception{
        //given
        Users user = new Users();
        user.setNickname("이준호");
        userRepository.save(user);

        //when
        OrderCoffeeDto orderCoffeeDto = createOrderCoffeeDto();
        orderService.orderCoffee(orderCoffeeDto);

        int totalPrice = getTotalPrice(orderCoffeeDto);

        //then

    }


}