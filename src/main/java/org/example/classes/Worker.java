package org.example.classes;

import org.example.enums.Period;
import org.example.enums.SoulState;
import org.example.enums.WorkStage;
import org.example.enums.WorkerRole;

import java.util.ArrayList;

public class Worker extends Person {
    private WorkerRole role;
    private boolean onVacation;
    private WorkStage currentWorkStage;

    public Worker(String name, Location location, SoulState state, ArrayList<Item> items, int itemsLimit, WorkerRole role, boolean onVacation, WorkStage currentWorkStage) {
        super(name, location, state, items, itemsLimit);
        this.role = role;
        this.onVacation = onVacation;
        this.currentWorkStage = currentWorkStage;
    }
    public Worker(String name, SoulState state, ArrayList<Item> items, int itemsLimit, WorkerRole role, boolean onVacation, WorkStage currentWorkStage) {
        this(name, null, state, items, itemsLimit, role, onVacation, currentWorkStage);
    }

    public Worker(String name, Location location, SoulState state, ArrayList<Item> items, int itemsLimit) {
        this(name, location, state, items, itemsLimit, WorkerRole.UNRANKED, false, WorkStage.DO_NOTHING);
    }
    public Worker(String name, Location location, SoulState state, int itemsLimit) {
        this(name, location, state, null, itemsLimit);
    }
    public Worker(String name, Location location, int itemsLimit) {
        this(name, location, SoulState.NORMAL, itemsLimit);
    }
    public Worker(String name, int itemsLimit) {
        this(name, null, SoulState.NORMAL, itemsLimit);
    }

    public WorkerRole getRole() {
        return role;
    }

    public void setRole(WorkerRole role) {
        this.role = role;
    }

    public boolean isOnVacation() {
        return onVacation;
    }

    public void setVacationStatus(boolean onVacation) {
        this.onVacation = onVacation;
        if (onVacation) System.out.printf("%s отправляется в отпуск\n", this);
        else System.out.printf("%s возвращается из отпуска\n", this);
    }
    public void setVacationStatus(boolean onVacation, Period p) {
        this.onVacation = onVacation;
        if (onVacation) System.out.printf("%s отправляется в отпуск на %s\n", this, p.getDescription());
        else System.out.printf("%s временно возвращается из отпуска на %s\n", this, p.getDescription());
    }

    public WorkStage getCurrentWorkStage() {
        return currentWorkStage;
    }

    public void setCurrentWorkStage(WorkStage currentWorkStage) {
        this.currentWorkStage = currentWorkStage;
        System.out.printf("%s сейчас %s\n", this, this.currentWorkStage);
    }

    public void printCurrentWorkStage() {
        System.out.printf("%s говорит, что на данный момент %s\n", this, currentWorkStage);
    }

    @Override
    public String toString() {
        return this.role.getDescription() + " " + this.name;
    }

    @Override
    public Worker clone() {
        Worker clone = new Worker(this.name, this.location, this.getState(), new ArrayList<>(this.getItems()), this.getItemsLimit(), this.role, this.onVacation, this.currentWorkStage);
        return clone;
    }


}
