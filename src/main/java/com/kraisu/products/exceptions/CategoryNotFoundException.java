package com.kraisu.products.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Category with id " + id + " not found");
    }
}
