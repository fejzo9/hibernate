package com.example.hibernate.model;

import java.util.List;

public record UpdateShop(String name, List<Part> parts) {
}
