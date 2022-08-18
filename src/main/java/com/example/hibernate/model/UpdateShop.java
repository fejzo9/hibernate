package com.example.hibernate.model;

import java.util.List;

public record UpdateShop(String name, List<Part> parts) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Part> parts() {
        return parts;
    }
}
