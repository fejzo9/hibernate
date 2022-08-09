package com.example.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "sifra")
    private UUID id;
    @Column(name = "year")
    private int yearOfManufacture;
    @Column(name = "broj_sasije")
    private String registerNumber;
}
