package com.kraisu.products.exceptions;

import java.util.UUID;

public class BlockListNotFoundExceptions extends RuntimeException {
    public BlockListNotFoundExceptions(UUID id) {
        super("Blocked word from BlockList with id " + id + " not found");
    }
}
