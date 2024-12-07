package org.example.classes;

import org.example.abstract_classes.Soul;
import org.example.enums.ItemState;
import org.example.enums.SoulState;
import org.example.exceptions.NoObjectInThisLocationException;
import org.example.exceptions.NoSpaceException;
import org.example.interfaces.Action;
import org.example.interfaces.Speakable;
import org.example.records.Speach;

import java.util.ArrayList;

public class Person extends Soul implements Speakable {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> wishItems = new ArrayList<>();
    private int itemsLimit;

    public Person(String name, Location location, SoulState state, ArrayList<Item> items, int itemsLimit) {
        super(name, location, state);
        this.items = items != null ? items : new ArrayList<>();
        this.itemsLimit = itemsLimit;
    }
    public Person(String name, Location location, ArrayList<Item> items, int itemsLimit) {
        this(name, location, SoulState.NORMAL, items, itemsLimit);
    }
    public Person(String name, Location location, int itemsLimit) {
        this(name, location, null, itemsLimit);
    }
    public Person(String name, int itemsLimit) {
        this(name, null, itemsLimit);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean takeItem(Item i) throws NoSpaceException, NoObjectInThisLocationException {
        if (this.items.size() + 1 > itemsLimit) {
            throw new NoSpaceException(String.format("Недостаточно места для добавление вещи в инвентарь персонажа %s", this.name));
        }
        if (location != null) {
            if (!i.getLocation().equals(this.location)) {
                throw new NoObjectInThisLocationException(String.format("Локация %s не содержит предмета %s", this.location.getName(), i.getName()));
            }
        }
        else if (i.getLocation() != null) {
            throw new NoObjectInThisLocationException("Невозможно добавить предмет в инвентарь: персонаж не находится на локации");
        }

        if (i.getState().equals(ItemState.HIDDEN)) {
            if (Math.random() < 0.6) {
                System.out.printf("у %s не получилось найти %s\n", this, i);
                return false;
            }
        }
        i.setState(ItemState.NORMAL);
        items.add(i);
        System.out.printf("Персонаж %s берет %s\n", this.name, i.getName());
        this.location.removeItem(i);

        if (wishItems.contains(i)) {
            System.out.printf("%s, получив %s удовлетворил свое желание!\n", this, i);
            wishItems.remove(i);
        }

        return true;
    }

    public void clearItems() {
        items.clear();
    }

    public void addAllItems(ArrayList<Item> newItems) throws NoSpaceException , NoObjectInThisLocationException{
        if (this.items.size() + newItems.size() > itemsLimit) {
            throw new NoSpaceException(String.format("Недостаточно места для добавление вещей в инвентарь персонажа %s", this.name));
        }
        for (Item i : newItems) {
            takeItem(i);
        }
    }

    @Override
    public void setLocation(Location newLocation) {
        if (this.getState().equals(SoulState.DRUNK)) {
            if (Math.random() > 0.5) {
                System.out.printf("%s НАСТЛЬКО ПЬЯН, что не в состоянии покинуть %s. Он остается здесь.\n", this, this.location);
                return;
            }
        }
        super.setLocation(newLocation);
    }

    public int getItemsLimit() {
        return itemsLimit;
    }

    public void setItemsLimit(int itemsLimit) {
        this.itemsLimit = itemsLimit;
        System.out.printf("у %s изменена вместимость инвентаря. Теперь в него помещается %d\n", this.name, this.itemsLimit);
    }

    public ArrayList<Item> getWishItems() {
        return wishItems;
    }

    public void addWishItem(Item i) {
        this.wishItems.add(i);
        System.out.printf("%s хочет %s\n", this, i);
    }

    public boolean lookAt(Person p) {
        if (!p.location.equals(this.location)) {
            throw new NoObjectInThisLocationException("Невозможно помотреть на персонажа: его нет на этой локации");
        }
        if (Math.random() < 0.5) {
            System.out.println(this + " заметил " + p);
            return true;
        }
        System.out.println(this + " не замечает " + p);
        return false;

    }

    @Override
    public void drink(BottleWithLiquid bottle) {
        this.setState(SoulState.NORMAL);
        bottle.setFull(false);
        System.out.printf("%s пьет %s, уталяя жажду.\n", this.name, bottle.getName());

        if (bottle instanceof AlcoholicBottle) {
            if (Math.random() > (double) (127 - ((AlcoholicBottle) bottle).getAlcoPercent()) / 127) {
                this.setState(SoulState.DRUNK);
                System.out.printf("%s пьянеет.\n", this);
            }
        }
    }

    @Override
    public void say(Speach s) {
        System.out.printf("%s говорит: [НАЧАЛО ЦИТАТЫ]\n%s\n[КОНЕЦ ЦИТАТЫ]\n", s.person(), s.text());
    }

    @Override
    public void explain(Action action) {
        System.out.printf("%s рассказывает: [НАЧАЛО РАССКАЗА]\n", this);
        action.run();
        System.out.printf("\n[КОНЕЦ РАССКАЗА] - %s закончил рассказ\n", this);
    }

    @Override
    public void askWhy(Action action) {
        System.out.printf("%s интересуется, почему произошло это событие: [НАЧАЛО РАССКАЗА]\n", this);
        action.run();
        System.out.println("[КОНЕЦ РАССКАЗА]");
    }

    @Override
    public void askWhyNot(Action action) {
        System.out.printf("Но почему же, спросил %s, не произошло это событие: [НАЧАЛО РАССКАЗА]\n", this);
        action.run();
        System.out.println("[КОНЕЦ РАССКАЗА]");
    }

    @Override
    public void agree() {
        System.out.printf("%s соглашается\n", this);
    }

    @Override
    public void disagree() {
        System.out.printf("%s: не соглашается\n", this);
    }

    @Override
    public String toString() {
        return "Персонаж \"" + this.name + "\"";
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result += items != null ? items.hashCode() : 0;
        result += 19 * itemsLimit;
        result += wishItems != null ? wishItems.hashCode() : 0;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person p = (Person) o;
        if (!name.equals(p.name) || itemsLimit != p.getItemsLimit()) return false;
        if (p.location == null && location != null || p.location != null && location == null) {
            return false;
        }
        assert location != null;
        return items.equals(p.items) && wishItems.equals(p.wishItems) && location.equals(p.location);
    }

    @Override
    public Person clone() {
        Person clone = new Person(this.name, this.location, this.getState(), new ArrayList<>(this.items), this.itemsLimit);
        clone.wishItems = new ArrayList<>(this.wishItems);
        return clone;
    }
}
