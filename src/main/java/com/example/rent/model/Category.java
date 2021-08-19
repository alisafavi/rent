package com.example.rent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "Categories")
@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Product> product;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}
