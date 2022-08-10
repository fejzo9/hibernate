package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    //fields
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "manufacturer")
    List<Model> models = new ArrayList<>();

    //constructors
    public Manufacturer(){}
    public Manufacturer(String name, List<Car> cars) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cars = cars;
    }

    //getters & setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
