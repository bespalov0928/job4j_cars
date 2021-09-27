package ru.job4j.cars.controller;

import javax.persistence.*;

@Entity
@Table(name = "brends")
public class Brend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "modelid")
    private Model model;

    public Brend(String name) {
        this.name = name;
    }

    public Brend() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
