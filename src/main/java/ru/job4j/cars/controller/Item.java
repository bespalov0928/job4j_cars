package ru.job4j.cars.controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @OneToOne
    @JoinColumn(name = "carId")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Foto> fotos = new ArrayList<>();


    private boolean sold;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

}
