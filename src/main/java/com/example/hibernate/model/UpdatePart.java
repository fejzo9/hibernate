package com.example.hibernate.model;

import java.util.List;

public record UpdatePart (String name, Double price, Rang rang, List<Model> models){
    @Override
    public String name() {
        return name;
    }

    @Override
    public Double price() {
        return price;
    }

    @Override
    public Rang rang() {
        return rang;
    }

    @Override
    public List<Model> models() {
        return models;
    }
}
