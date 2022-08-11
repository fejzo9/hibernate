package com.example.hibernate.model;

import javax.persistence.*;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    //fields
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "year")
    private int yearOfManufacture;
    @Column(name = "registerNumber")
    private String registerNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    //constructors
    public Car() {
    }

    public Car (int yearOfManufacture, String registerNumber)
    {
        this.yearOfManufacture = yearOfManufacture;
        this.registerNumber = registerNumber;
    }
    public Car(int yearOfManufacture, User user) {
        this.id = UUID.randomUUID();
        this.yearOfManufacture = yearOfManufacture;
        this.user = user;
    }

    public Car(int yearOfManufacture, String registerNumber, User user) {
        this.id = UUID.randomUUID();
        this.yearOfManufacture = yearOfManufacture;
        this.registerNumber = registerNumber;
        this.user = user;
    }

    //getters & setters
    public UUID getId() {
        return id;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
