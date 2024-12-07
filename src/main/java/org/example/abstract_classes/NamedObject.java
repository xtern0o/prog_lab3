package org.example.abstract_classes;

public abstract class NamedObject {
    protected String name;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    abstract public String toString();
    abstract public int hashCode();
    abstract public boolean equals(Object obj);
}
