package ru.sergeykozyakov.skoobyfacebot.entities;

import java.util.List;

/**
 * Interface indicates that an entity must store and return keyboards list
 *
 * @author Sergey Kozyakov
 */
public interface Keyboardable {
    /**
     * Returns the list of keyboards or {@code null} if it does not exist
     *
     * @return list of keyboards
     */
    List<Keyboard> getKeyboards();
}
