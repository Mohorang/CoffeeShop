package com.example.coffeeshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Coffee {
    @Id @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //커피이름
    @Column
    private String name;
    //가격
    private int price;

    @OneToMany(mappedBy = "coffee" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
