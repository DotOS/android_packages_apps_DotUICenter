package com.dot.uicenter.utils.argb_picker;

/**
 * The Subject interface,
 * implemented by classes that need to
 * notify Observers of changes that
 * they are interested in.
 */
public interface Subject {

    void register(Observer observer);

    void unRegister(Observer observer);

    void notifyObservers();

}
