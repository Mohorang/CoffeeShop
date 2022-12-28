package com.example.coffeeshop.repository;

import com.example.coffeeshop.domain.Coffee;
import com.example.coffeeshop.domain.Order;
import com.example.coffeeshop.domain.OrderDetail;
import com.example.coffeeshop.dto.responseDto.PopularCoffee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order , Long> {

    @Query(value = "select sum(d.quantity) from OrderDetail d inner join Order o on d.order.id = o.id where o.orderedAt > :sevenDays group by d.coffee.id order by SUM(d.quantity) DESC")
    List<Integer> findAllPopularCoffeeId(@Param("sevenDays")LocalDateTime sevenDays , Pageable limit3);
}
