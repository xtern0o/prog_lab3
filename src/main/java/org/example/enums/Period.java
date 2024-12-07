package org.example.enums;

public enum Period {
    ALL_DAY("весь день"),
    ALL_MORNING("все утро"),
    ALL_EVENING("весь вечер"),
    ALL_NIGHT("всю ночь");

    private String description;

    Period(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return description;
    }
}
