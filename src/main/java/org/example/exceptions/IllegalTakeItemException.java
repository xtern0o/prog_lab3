package org.example.exceptions;

import org.example.classes.Item;
import org.example.classes.Person;

public class IllegalTakeItemException extends RuntimeException {
    public Person person;
    public Item item;

    public IllegalTakeItemException(Person person, Item item) {
        super();
        this.person = person;
        this.item = item;
    }

    @Override
    public String getMessage() {
        return String.format("%s, находясь на %s не может взять %s - он на локации %s", person, person.getLocation(), item, item.getLocation());
    }
}
