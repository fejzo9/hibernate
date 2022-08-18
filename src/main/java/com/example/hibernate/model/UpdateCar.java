package com.example.hibernate.model;

public record UpdateCar (int yearOfManufacture, String registerNumber, User user, Manufacturer manufacturer) {
    @Override
    public int yearOfManufacture() {
        return yearOfManufacture;
    }

    @Override
    public String registerNumber() {
        return registerNumber;
    }

    @Override
    public User user() {
        return user;
    }

    @Override
    public Manufacturer manufacturer() {
        return manufacturer;
    }
}
