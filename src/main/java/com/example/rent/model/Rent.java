package com.example.rent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Getter
@Setter
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    int price,rate;

    Long location;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinish;

    @OneToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private User user;


    public Rent(int price, int rate, Long location) {
        this.price = price;
        this.rate = rate;
        this.location = location;
    }

    public Rent() {

    }

    public Rent(Rent rent) {

    }
}
