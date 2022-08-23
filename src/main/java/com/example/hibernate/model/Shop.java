package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Shop")
public class Shop {

    //fields
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "name ")
    private String name;
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    List<Part> parts = new ArrayList<>();

    //constructors
    public Shop(){}
    public Shop(String name, List<Part> parts) {
        this.name = name;
        this.parts = parts;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
