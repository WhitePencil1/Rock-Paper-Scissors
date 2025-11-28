package com.whitepencil.rps.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String id) {
        super("Player with id " + id + " not found");
    }
}
