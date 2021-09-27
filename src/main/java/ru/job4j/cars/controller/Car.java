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

    @ManyToOne
    @JoinColumn(name = "modelid")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "brendid")
    private Brend brend;

    public Car(Brend brend, Model model, TypeBody typeBody) {
        this.brend = brend;
        this.model = model;
        this.typeBody = typeBody;
        this.name = brend.getName();
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TypeBody getTypeBody() {
        return typeBody;
    }

    public Model getModel() {
        return model;
    }

    public Brend getBrend() {
        return brend;
    }
}
