package com.example.rent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "products")
@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name,attributes;
    private String productImg;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;


    public Product(String name, String attributes, User user, Category category) {
        this.name = name;
        this.attributes = attributes;
        this.user = user;
        this.category = category;
    }

    public Product() {

    }
}
