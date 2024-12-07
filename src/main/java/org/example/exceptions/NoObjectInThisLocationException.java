package org.example.exceptions;

public class NoObjectInThisLocationException extends RuntimeException {
    public NoObjectInThisLocationException(String message) {
        super(message);
    }
}
