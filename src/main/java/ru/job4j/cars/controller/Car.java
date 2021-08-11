package ru.job4j.cars.controller;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private TypeBody typeBody;

    public Car(String name, TypeBody typeBody) {
        this.name = name;
        this.typeBody = typeBody;
    }

    public Car() {
    }


}
