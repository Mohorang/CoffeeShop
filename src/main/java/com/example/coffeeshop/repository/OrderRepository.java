package com.example.coffeeshop.repository;

import com.example.coffeeshop.domain.Order;
import com.example.coffeeshop.mapping.CoffeeIdAndSumDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order , Long> {

    //Query DSL 로 바꿔보기
    @Query(value = "select d.coffee.id as id,sum(d.price) as price from OrderDetail d inner join Order o on d.order.id = o.id where o.orderedAt > :sevenDays group by d.coffee.id order by sum(d.price) desc")
    List<CoffeeIdAndSumDto> findAllPopularCoffeeId(@Param("sevenDays")LocalDateTime sevenDays , Pageable limit3);
}
