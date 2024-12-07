package org.example.enums;

public enum WorkerRole {
    DUKE("Герцог"),
    BARON("Барон"),
    RAGMAN("Тряпочник"),
    UNRANKED("<без роли>");


    private String description;

    WorkerRole(String description) {
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
