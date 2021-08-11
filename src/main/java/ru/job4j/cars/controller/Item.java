package ru.job4j.cars.controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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


     @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateItem;

    private boolean sold;

    public Item(String description, Car car, List<Foto> fotos, boolean sold, Account account, Date dateItem) {
        this.description = description;
        this.car = car;
        this.fotos = fotos;
        this.sold = sold;
        this.account = account;
        this.dateItem = dateItem;
    }

    public Item() {
    }
}
