package com.example.coffeeshop.repository;

import com.example.coffeeshop.domain.QOrder;
import com.example.coffeeshop.domain.QOrderDetail;
import com.example.coffeeshop.dto.responseDto.CoffeeRank;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.coffeeshop.domain.QOrder.*;
import static com.example.coffeeshop.domain.QOrderDetail.*;

@Repository
public class OrderRankRepository {
    private final JPAQueryFactory queryFactory;

    public OrderRankRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<CoffeeRank> CoffeeRankIn7Days(){

        LocalDateTime date = LocalDateTime.now().minusDays(7);

        return queryFactory
                .select(Projections.fields(CoffeeRank.class,
                        orderDetail.coffee.id, orderDetail.price.sum().as("price")))
                .from(orderDetail)
                .innerJoin(orderDetail.order, order)
                .on(orderDetail.order.id.eq(order.id))
                .where(order.orderedAt.gt(date))
                .groupBy(orderDetail.coffee.id)
                .orderBy(orderDetail.price.sum().desc())
                .limit(3)
                .fetch();
    }
}
