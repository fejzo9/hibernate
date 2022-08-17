package com.example.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parts")
public class Part {
    //fields
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Double price;
    @Column(name="rang")
    private Rang rang;

    //relationships
    @ManyToMany
    @JoinTable(name="part_model",
    joinColumns = @JoinColumn(name="part_id"),
    inverseJoinColumns = @JoinColumn(name="model_id"))
    private List<Model> models = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    Shop shop;

    //constructors
    public Part() {
    }
    public Part(String name) {
        this.name = name;
    }

    public Part(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Part(String name, Double price, Rang rang, List<Model> models, Shop shop) {
        this.name = name;
        this.price = price;
        this.rang = rang;
        this.models = models;
        this.shop = shop;
    }

    //getters & setters

    public UUID getId() {
        return id;
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

    public List<Model> getModels() {
        return models;
    }
    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
