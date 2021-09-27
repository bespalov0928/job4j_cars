package ru.job4j.cars.controller;

import javax.persistence.*;

@Entity
@Table(name = "fotos")
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String pathName;

    public Foto(String pathName) {
        this.pathName = pathName;
    }

    public Foto() {

    }

    public String getPathName() {
        return pathName;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "pathName='" + pathName + '\'' +
                '}';
    }
}
