package com.example.coffeeshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    //생성날짜
    @CreatedDate
    private LocalDateTime createdAt;

    //수정날짜
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    //주문횟수
    private int orderCount;

    @OneToMany(mappedBy = "coffee" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Coffee(String name,int price){
        this.name = name;
        this.price= price;
        this.createdAt = LocalDateTime.now();
    }
}
