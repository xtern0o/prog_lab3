package org.example.classes;

import org.example.enums.ItemState;

public class AlcoholicBottle extends BottleWithLiquid {
    byte alcoPercent;

    public AlcoholicBottle(String name, Location location, ItemState state, boolean full, byte alcoPercent) {
        super(name, location, state, full);
        this.alcoPercent = alcoPercent;
    }
    public AlcoholicBottle(String name, boolean full, byte alcoPercent) {
        super(name, full);
        this.alcoPercent = alcoPercent;
    }
    public AlcoholicBottle(String name, ItemState state, boolean full, byte alcoPercent) {
        super(name, state, full);
        this.alcoPercent = alcoPercent;
    }
    public AlcoholicBottle(String name, Location location, boolean full, byte alcoPercent) {
        super(name, location, full);
        this.alcoPercent = alcoPercent;
    }

    public byte getAlcoPercent() {
        return alcoPercent;
    }

    public void setAlcoPercent(byte alcoPercent) {
        this.alcoPercent = alcoPercent;
    }

    @Override
    public String toString() {
        return String.format("Алкогольный напиток %s. Крепость: %d/127 (%s)", this.name, this.alcoPercent, (this.isFull() ? "полный" : "пустой"));
    }
}
