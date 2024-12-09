package org.example.exceptions;

import org.example.abstract_classes.Soul;

public class IllegalLookingException extends RuntimeException {
    public Soul o1_looks;
    public Soul o2;

    public IllegalLookingException(Soul o1, Soul o2) {
        super();
        this.o1_looks = o1;
        this.o2 = o2;
    }

    @Override
    public String getMessage() {
        return String.format("%s, находясь на %s не может взаимодействовать с %s, который находится на %s", o1_looks, o1_looks.getLocation(), o2, o2.getLocation());
    }
}
