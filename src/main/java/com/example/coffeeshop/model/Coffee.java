package com.example.coffeeshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Coffee {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private int price;
    private int orderCount;

    public Coffee(String name,int price){
        this.name = name;
        this.price=price;
    }

    public void orderCountUp(){
        this.orderCount++;
    }
}
