package com.example.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    //fields
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "year")
    private int yearOfManufacture;
    @Column(name = "registerNumber")
    private String registerNumber;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    //constructors
    public Car() {
    }

    public Car(int yearOfManufacture, String registerNumber) {
        this.yearOfManufacture = yearOfManufacture;
        this.registerNumber = registerNumber;
    }

    public Car(int yearOfManufacture, User user) {
        this.yearOfManufacture = yearOfManufacture;
        this.user = user;
    }

    public Car(int yearOfManufacture, String registerNumber, User user) {
        this.yearOfManufacture = yearOfManufacture;
        this.registerNumber = registerNumber;
        this.user = user;
    }

    public Car(int yearOfManufacture, String registerNumber, User user, Manufacturer manufacturer) {
        this.yearOfManufacture = yearOfManufacture;
        this.registerNumber = registerNumber;
        this.user = user;
        this.manufacturer = manufacturer;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
