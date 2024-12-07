package org.example.enums;

public enum WorkStage {
    ESCORTING("сопровождает"),
    ESCORTING_THE_BARON("сопровождает барона"),
    DO_NOTHING("ничего не делает");

    private String description;

    WorkStage(String description) {
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
