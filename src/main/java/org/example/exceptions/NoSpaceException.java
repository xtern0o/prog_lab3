package org.example.exceptions;

public class NoSpaceException extends RuntimeException {
    public NoSpaceException(String message) {
        super(message);
    }
}
