package com.example.rent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Getter @Setter
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @NotEmpty(message = "*Please provide your username name")
    private String userName;
//    @NotEmpty(message = "*Please provide your name")
    private String firstName;
//    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
//    @Length(min = 5, message = "*Your password must have at least 5 characters")
//    @NotEmpty(message = "*Please provide your password")
    private String password;
    private String roles ;
    private boolean active;

    private String profImage;

    public User(String userName, String firstName, String lastName, String password, String roles, boolean active) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public User() {

    }
}
