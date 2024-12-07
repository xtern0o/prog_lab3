package org.example.enums;

public enum ItemState {
    NORMAL(""),
    HIDDEN("спрятан");

    private final String description;

    ItemState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
