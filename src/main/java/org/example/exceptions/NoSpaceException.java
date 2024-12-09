package org.example.exceptions;

import org.example.abstract_classes.NamedObject;

public class NoSpaceException extends RuntimeException {
    public NamedObject object;
    public NamedObject addingObject;

    public NoSpaceException(NamedObject object, NamedObject addingObject) {
        super();
        this.object = object;
        this.addingObject = addingObject;
    }

    @Override
    public String getMessage() {
        return String.format("Невозможно добавить %s. Внутри %s закончилось место для хранения.", addingObject, object);
    }
}
