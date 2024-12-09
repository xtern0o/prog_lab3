package org.example.classes;

import org.example.abstract_classes.NamedObject;
import org.example.abstract_classes.Soul;
import org.example.exceptions.IllegalTakeItemException;
import org.example.exceptions.NoSuchObjectInLocationException;

import java.util.ArrayList;
import java.util.Iterator;

public class Location extends NamedObject {
    protected ArrayList<Soul> souls = new ArrayList<>();
    protected ArrayList<Item> items = new ArrayList<>();

    public Location(String name, ArrayList<Item> items, ArrayList<Soul> souls) {
        this.setName(name);
        this.items.addAll(items);
        this.souls.addAll(souls);
    }
    public Location(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    public void addSoul(Soul s) {
        if (!souls.contains(s)) {
            souls.add(s);
        }
    }
    public ArrayList<Soul> getSouls() {
        return souls;
    }

    public void addItem(Item i) {
        if (!items.contains(i)) {
            items.add(i);
        }
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean removeItem(Item i) {
        if (i.getLocation().equals(this) && items.contains(i)) {
            items.remove(i);
            return true;
        }
        else {
            throw new NoSuchObjectInLocationException(i, this);
        }
    }
    public boolean removeSoul(Soul s) {
        if (s.getLocation().equals(this) && souls.contains(s)) {
            souls.remove(s);
            return true;
        }
        else {
            throw new NoSuchObjectInLocationException(s, this);
        }
    }

    public void clearItems() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item i = iterator.next();
            iterator.remove();
        }

    }
    public void clearSouls() {
        Iterator<Soul> iterator = souls.iterator();
        while (iterator.hasNext()) {
            Soul s = iterator.next();
            iterator.remove();
        }
    }
    public void clear() {
        clearItems();
        clearSouls();
        System.out.printf("%s полностью очищена.\n", this);
    }

    @Override
    public String toString() {
        return "Локация " + this.name;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result += souls.hashCode();
        result += items.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Location loc = (Location) obj;
        if (!this.name.equals(loc.name)) return false;
        return this.getSouls().equals(loc.getSouls()) && this.getItems().equals(loc.getItems());
    }
}
