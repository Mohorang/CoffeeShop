package com.example.coffeeshop.domain;

import lombok.Getter;
import lombok.Setter;
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

    @Setter
    private long totalPrice;

    public void addOrderDetail(Users user,OrderDetail orderDetail) {
        this.totalPrice += orderDetail.getPrice();

        orderDetails.add(orderDetail);

        this.user = user;
        user.getOrders().add(this);

        this.orderedAt = LocalDateTime.now();
    }



    private void setOrderDetail(Users user, OrderDetail orderDetail) {
        orderDetails.add(orderDetail);

        this.user = user;
        user.getOrders().add(this);
    }
}
