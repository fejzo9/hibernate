package com.example.hibernate.model;

import java.util.List;

public record UpdateManufacturer(String name, List<Car> cars, List<Model> models) {
}
