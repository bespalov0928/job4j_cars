package ru.job4j.cars.controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "typeBodes")
public class TypeBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public TypeBody(String name) {
        this.name = name;
    }

    public TypeBody() {
    }
}
