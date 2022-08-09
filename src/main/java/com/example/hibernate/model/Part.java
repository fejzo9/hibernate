package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @Column(name = "sifra")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Double price;
    @Column(name="rang")
    private Rang rang;

    @ManyToMany
    @JoinTable(name="part_model",
    joinColumns = @JoinColumn(name="id"),
    inverseJoinColumns = @JoinColumn(name="id"))
    private List<Model> models = new ArrayList<>();

    public Part() {
    }

    public Part(String name) {

    }

    public Part(String name, Double price) {
        this.name = name;
        this.price = price;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }

    public List<Model> getModeli() {
        return models;
    }

    public void setModeli(List<Model> modeli) {
        this.models = modeli;
    }
}
