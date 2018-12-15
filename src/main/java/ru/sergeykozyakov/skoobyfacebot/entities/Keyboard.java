package ru.sergeykozyakov.skoobyfacebot.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * XML Keyboard entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement
public class Keyboard {
    /**
     * Keyboard id attribute
     */
    private String id;

    /**
     * List of keys
     */
    private List<Key> keyboard;

    /**
     * Returns the keyboard id attribute or {@code null} if it does not exist
     *
     * @return keyboard id attribute
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the keyboard id attribute
     *
     * @param id keyboard id attribute
     */
    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the list of keys or {@code null} if it does not exist
     *
     * @return list of keys
     */
    public List<Key> getKeyboard() {
        return keyboard;
    }

    /**
     * Sets the list of keys
     *
     * @param keyboard list of keys
     */
    @XmlElement(name = "key")
    public void setKeyboard(List<Key> keyboard) {
        this.keyboard = keyboard;
    }
}
