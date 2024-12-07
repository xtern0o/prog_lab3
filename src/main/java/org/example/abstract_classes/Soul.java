package org.example.abstract_classes;

import org.example.classes.BottleWithLiquid;
import org.example.classes.Location;
import org.example.classes.Room;
import org.example.enums.SoulState;

public abstract class Soul extends NamedObject {
    private SoulState state;
    protected Location location;

    public Soul(String name, Location location, SoulState state) {
        this.setName(name);
        this.setLocation(location);
        this.setState(state);
    }
    public Soul(String name, Location location) {
        this(name, location, SoulState.NORMAL);
    }
    public Soul(String name) {
        this(name, null, SoulState.NORMAL);
    }
    public Soul(String name, SoulState state) {
        this(name, null, state);
    }

    public SoulState getState() {
        if (state == null) return SoulState.NORMAL;
        return state;
    }

    public void setState(SoulState state) {
        this.state = state;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLocation) {
        if (newLocation == null) {
            if (location != null) {
                System.out.printf("%s покидает локацию %s\n", this, this.location);
                this.location.removeSoul(this);
                this.location = null;
            }
        }
        else {
            if (location != null) {
                this.location.removeSoul(this);
            }
            this.location = newLocation;
            newLocation.addSoul(this);

            if (newLocation instanceof Room) {
                System.out.printf("%s перемещается в комнату: \"%s\"\n", this.name, newLocation.name);
            }
            else {
                System.out.printf("%s перемещается на локацию \"%s\"\n", this.name, newLocation.name);
            }
        }
    }

    public boolean isAloneAtLocation() {
        return this.location.getSouls().size() == 1;
    }

    public void printState() {
        System.out.printf("Состояние %s: %s\n", this, state);
    }

    public abstract void drink(BottleWithLiquid bottle);

    public abstract Soul clone();
}
