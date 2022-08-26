package com.example.hibernate.model;

import java.util.List;

public record UpdateModel(String name, List<Part> parts) {
}
