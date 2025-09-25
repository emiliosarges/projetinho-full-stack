package com.example.people.service;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
