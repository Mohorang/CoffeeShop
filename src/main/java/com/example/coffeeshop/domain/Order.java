package com.example.coffeeshop.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDateTime orderedAt;

    private int totalPrice;


    public void addOrder(Users user,OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);

        this.user = user;
        user.getOrders().add(this);

        calculateTotalPrice(orderDetail.getPrice());
        this.orderedAt = LocalDateTime.now();
    }
    public void calculateTotalPrice(int totalPrice){
        this.totalPrice += totalPrice;
        //유저 포인트 차감
        this.user.usePoint(totalPrice);
    }
}
