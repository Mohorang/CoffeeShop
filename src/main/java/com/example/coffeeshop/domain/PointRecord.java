package com.example.coffeeshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "point_record")
@Getter
public class PointRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_record_id")
    private Long id;

    private long point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Enumerated(value = EnumType.STRING)
    private PointStatus pointStatus;

    public PointRecord(Users user,long point,PointStatus pointStatus){
        this.user = user;
        this.point = point;
        this.pointStatus = pointStatus;
        //this.user.usePoint(point);
        //       this.setUser(user);
    }
}
