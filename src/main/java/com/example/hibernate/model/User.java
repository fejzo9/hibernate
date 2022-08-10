package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    //fields
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user")
    List<Car> cars = new ArrayList<>();

    //constructors
    public User() {
    }

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
    public User(String name, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
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

    public String getAdress() {return address;}

    public void setAdress(String adress) {this.address = adress;}

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
