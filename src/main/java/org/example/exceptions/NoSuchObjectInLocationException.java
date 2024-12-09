package org.example.exceptions;

import org.example.abstract_classes.NamedObject;
import org.example.classes.Location;

public class NoSuchObjectInLocationException extends RuntimeException {
    public NamedObject object;
    public Location location;

    public NoSuchObjectInLocationException(NamedObject object, Location location) {
        super();
        this.object = object;
        this.location = location;
    }

    @Override
    public String getMessage() {
        return String.format("Объект %s не найден внутри %s", object, location);
    }
}
