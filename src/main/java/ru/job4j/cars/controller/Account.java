package ru.job4j.cars.controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;
    private String phone;

    public Account(String userName, String email, String phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public Account() {

    }
}
