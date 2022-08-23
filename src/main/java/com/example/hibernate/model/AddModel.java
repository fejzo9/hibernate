package com.example.hibernate.model;

import java.util.List;

public record AddModel(String name, List<Part> parts , Manufacturer manufacturer) {
}
