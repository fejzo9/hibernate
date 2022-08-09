package com.example.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Shop")
public class Shop {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name ")
    private String name;
//    @Column(name="cars")
//    private List<Car> cars = new ArrayList<>();
}
