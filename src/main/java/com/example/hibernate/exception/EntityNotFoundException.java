package com.example.hibernate.exception;

public class EntityNotFoundException extends NullPointerException{
    public EntityNotFoundException(String message)
    {
        super(message);
    }
}
