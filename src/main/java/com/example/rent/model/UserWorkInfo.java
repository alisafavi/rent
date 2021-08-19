package com.example.rent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter @Getter
@Entity
@Table
public class UserWorkInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String job;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    public UserWorkInfo() {
    }

}
