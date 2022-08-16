package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "models")
public class Model {
    //fields
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name="name")
    private String name;
    @ManyToMany(mappedBy = "models")
    private List<Part> parts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    //constructors
    public Model() {
    }
    public Model(String name) {
        this.name = name;
    }

    //getters & setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
