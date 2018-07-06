package com.dot.uicenter.utils.argb_picker;

/**
 * This is the Observer interface,
 * implemented by classes that need to
 * Observe Subjects.
 */
public interface Observer {

    /**
     * method to update the observer, used by subject to notify
     *
     * @param subject The subject which got updated
     * @param value   Updated colour value
     */
    void update(Subject subject, int value);

}
