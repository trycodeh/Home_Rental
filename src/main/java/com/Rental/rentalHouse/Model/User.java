package com.Rental.rentalHouse.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String type;
    private String password;

    public User(String name, String email, String mobile, String type, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.type = type;
        this.password=password;
    }




}
