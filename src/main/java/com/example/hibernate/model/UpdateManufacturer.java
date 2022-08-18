package com.example.hibernate.model;

import java.util.List;

public record UpdateManufacturer (String name, List<Car> cars, List<Model> models){
    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Car> cars() {
        return cars;
    }

    @Override
    public List<Model> models() {
        return models;
    }
}
