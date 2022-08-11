package com.example.hibernate.model;

import java.util.List;

public  record AddManufacturer(String name, List<Car> cars, List<Model> models) {
}
