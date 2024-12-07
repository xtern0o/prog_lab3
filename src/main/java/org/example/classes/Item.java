package org.example.classes;

import org.example.abstract_classes.NamedObject;
import org.example.enums.ItemState;

public class Item extends NamedObject {
    private ItemState state;
    private Location location;

    public Item(String name, Location location, ItemState state) {
        this.setName(name);
        if (location != null) this.setLocation(location, false);
        this.setState(state);
    }
    public Item(String name) {
        this(name, null, ItemState.NORMAL);
    }
    public Item(String name, ItemState state) {
        this(name, null, state);
    }
    public Item(String name, Location location) {
        this(name, location, ItemState.NORMAL);
    }

    public ItemState getState() {
        return state;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLocation) {
        if (newLocation == null) {
            System.out.printf("%s удален из %s\n", this, this.location);
            if (this.location != null) {
                this.location.removeItem(this);
            }
            this.location = null;
        }
        else {
            if (location != null) {
                this.location.removeItem(this);
            }
            this.location = newLocation;
            newLocation.addItem(this);

            if (newLocation instanceof Room) {
                System.out.printf("%s перемещается в комнату: \"%s\"\n", this.name, newLocation.getName());
            }
            else {
                System.out.printf("%s перемещается на локацию \"%s\"\n", this.name, newLocation.getName());
            }
        }
    }

    public void setLocation(Location newLocation, boolean log) {
        if (newLocation == null) {
            if (log) System.out.printf("%s удален из %s\n", this, this.location);
            if (this.location != null) {
                this.location.removeItem(this);
            }
            this.location = null;
        }
        else {
            if (location != null) {
                this.location.removeItem(this);
            }
            this.location = newLocation;
            newLocation.addItem(this);

            if (newLocation instanceof Room) {
                if (log) System.out.printf("%s перемещается в комнату: \"%s\"\n", this.name, newLocation.getName());
            }
            else {
                if (log) System.out.printf("%s перемещается на локацию \"%s\"\n", this.name, newLocation.getName());
            }
        }
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Предмет \"" + this.name + "\"" + (this.state.equals(ItemState.NORMAL) ? "" : " (" + this.state.getDescription() + ")");
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result += location != null ? location.hashCode() : 0;
        result += state.getDescription().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        if (state != item.state || !name.equals(item.name)) return false;
        if (location == null && item.location == null) return true;
        assert location != null;
        return location.equals(item.getLocation()) & name.equals(item.name);
    }
}
