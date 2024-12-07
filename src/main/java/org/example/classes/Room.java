package org.example.classes;

import org.example.abstract_classes.Soul;
import org.example.exceptions.NoSpaceException;

import java.util.ArrayList;

public class Room extends Location {
    private int soulsLimit;
    private int itemsLimit;

    public Room(String name, int itemsLimit, int soulsLimit) {
        super(name);
        this.soulsLimit = soulsLimit;
        this.itemsLimit = itemsLimit;
    }
    public Room(String name, ArrayList<Item> items, ArrayList<Soul> souls, int itemsLimit, int soulsLimit) {
        super(name, items, souls);
        this.soulsLimit = soulsLimit;
        this.itemsLimit = itemsLimit;
    }

    public int getSoulsLimit() {
        return soulsLimit;
    }

    public void setSoulsLimit(int soulsLimit) {
        this.soulsLimit = soulsLimit;
    }

    public int getItemsLimit() {
        return itemsLimit;
    }

    public void setItemsLimit(int itemsLimit) {
        this.itemsLimit = itemsLimit;
    }

    @Override
    public void addItem(Item i) throws NoSpaceException{
        if (this.items.size() + 1 > itemsLimit && !items.contains(i)) {
            throw new NoSpaceException("Недостаточно места для добавления предмета");
        }
        super.addItem(i);
    }
    @Override
    public void addSoul(Soul s) throws NoSpaceException {
        if (this.souls.size() + 1 > soulsLimit && !souls.contains(s)) {
            throw new NoSpaceException("Недостаточно места для добавления души");
        }
        super.addSoul(s);
    }

    @Override
    public String toString() {
        return "Комната \"" + this.name + "\"";
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result += souls.hashCode();
        result += items.hashCode();
        result = 19 * result + soulsLimit;
        result = 19 * result + itemsLimit;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        if (!this.name.equals(room.name)) return false;
        return this.getSouls().equals(room.getSouls()) && this.getItems().equals(room.getSouls()) &&
                this.soulsLimit == room.getSoulsLimit() && this.itemsLimit == room.getItemsLimit();
    }
}
