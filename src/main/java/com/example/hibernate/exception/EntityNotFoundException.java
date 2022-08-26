package com.example.hibernate.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Object id) {
        super("Entity '%s' with id = '%s' not found!".formatted(entity, id));
    }

}
