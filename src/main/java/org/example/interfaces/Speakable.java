package org.example.interfaces;

import org.example.records.Speach;

public interface Speakable {
    void say(Speach s);
    void explain(Action action);
    void askWhy(Action action);
    void askWhyNot(Action action);
    void agree();
    void disagree();
}
