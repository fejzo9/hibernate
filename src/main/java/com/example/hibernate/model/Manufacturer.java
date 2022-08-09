package com.example.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @Column(name = "sifra")
    private UUID id;

    @Column(name="name")
    private String name;

}
