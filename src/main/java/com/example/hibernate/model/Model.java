package com.example.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "models")
public class Model {
    //fields
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "models", fetch = FetchType.LAZY)
    private List<Part> parts = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    //constructors
    public Model() {
    }

    public Model(String name) {
        this.name = name;
    }

    public Model(String name, List<Part> parts) {
        this.name = name;
        this.parts = parts;
    }

    public Model(String name, List<Part> parts, Manufacturer manufacturer) {
        this.name = name;
        this.parts = parts;
        this.manufacturer = manufacturer;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
