package ru.sergeykozyakov.skoobyfacebot.entities.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * XML Key entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement
public class Key {
    /**
     *  Key value
     */
    private String value;

    /**
     * Returns the key value
     *
     * @return key value or {@code null} if it does not exist
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the key value
     *
     * @param value key value
     */
    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }
}