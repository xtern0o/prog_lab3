package org.example.enums;

public enum SoulState {
    NORMAL("чувствует себя нормально"),
    THIRST("чувствует жажду"),
    HUNGRY("Чувствует голод"),
    DRUNK("в состоянии алкогольного опьянения");

    private final String description;

    SoulState(String description) {
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
