package com.example.hibernate.model;

import java.util.List;

public record AddPart (String name, Double price, Rang rang, List<Model> models, Shop shop) {
}
