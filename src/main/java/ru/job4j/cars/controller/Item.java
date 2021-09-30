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
    @Column(name = "id")
    private int id;

    private String description;

    @OneToOne
    @JoinColumn(name = "carId")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Foto> fotos = new ArrayList<>();

     @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateItem;


    private boolean sold;

    public String imgBase64 = "";

    public Item(String description, Car car, boolean sold, Account account, Date dateItem) {
        this.description = description;
        this.car = car;
        this.sold = sold;
        this.account = account;
        this.dateItem = dateItem;
    }

    public Item() {
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Car getCar() {
        return car;
    }

    public Account getAccount() {
        return account;
    }

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

}
