package com.example.coffeeshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private long point;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PointRecord> pointRecords = new ArrayList<>();

    public Users(String nickname){
        this.nickname = nickname;
    }
    public void chargePoint(long point){
        this.point += point;
        PointRecord pointRecord = new PointRecord(this,point,PointStatus.CHARGE);
        pointRecords.add(pointRecord);
    }

    public void usePoint(long point){
        if(this.point < point){
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
        this.point -= point;
        PointRecord pointRecord = new PointRecord(this,point,PointStatus.USE);
        pointRecords.add(pointRecord);
    }
}
