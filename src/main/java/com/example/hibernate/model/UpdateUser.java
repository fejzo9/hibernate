package com.example.hibernate.model;

public record UpdateUser(
        String name,
        String address
) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String address() {
        return address;
    }
}
