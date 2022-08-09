package com.example.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name ")
    private String name;

    @Column(name = "adress ")
    private String adress;

    public User() {
    }

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
    public User(String name, String adress) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.adress = adress;
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

    public String getAdress() {return adress;}

    public void setAdress(String adress) {this.adress = adress;}
}
