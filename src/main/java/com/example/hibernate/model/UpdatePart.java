package com.example.hibernate.model;

import java.util.List;

public record UpdatePart (String name, Double price, Rang rang, List<Model> models){
}
