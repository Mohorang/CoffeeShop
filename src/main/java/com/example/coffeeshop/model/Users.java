package com.example.coffeeshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int point;

    public void chargePoint(int point){
        this.point += point;
    }

    public void usePoint(int point){
        this.point -= point;
    }
}
