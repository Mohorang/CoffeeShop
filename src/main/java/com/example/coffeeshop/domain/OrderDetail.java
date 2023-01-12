package com.example.coffeeshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coffee coffee;

    //수량
    private int quantity;

    //가격
    private int price;

    public OrderDetail(Order order , Coffee coffee,int quantity){
        this.order = order;
        this.coffee = coffee;
        this.quantity = quantity;
        this.price = coffee.getPrice()*quantity;
    }
}
