package org.example.classes;

import org.example.enums.ItemState;

public class BottleWithLiquid extends Item {
    private boolean full;

    public BottleWithLiquid(String name, Location location, ItemState state, boolean full) {
        super(name, location, state);
        this.full = full;
    }

    public BottleWithLiquid(String name, boolean full) {
        super(name);
        this.full = full;
    }

    public BottleWithLiquid(String name, ItemState state, boolean full) {
        super(name, state);
        this.full = full;
    }

    public BottleWithLiquid(String name, Location location, boolean full) {
        super(name, location);
        this.full = full;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

}
