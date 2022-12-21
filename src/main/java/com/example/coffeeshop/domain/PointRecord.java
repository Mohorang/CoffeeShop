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

    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Enumerated(value = EnumType.STRING)
    private PointStatus pointStatus;

    public void setUser(Users user){
        this.user = user;
        this.user.getPointRecords().add(this);
    }

    public void addPoint(Users user,int point) {
        this.setUser(user);
        this.point = point;
        this.pointStatus = PointStatus.CHARGE;
    }

    public void usePoint(Users user,int point){
        this.setUser(user);
        this.point = point;
        this.pointStatus = PointStatus.USE;
    }
}
