package ru.sergeykozyakov.skoobyfacebot.entities.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * XML Route entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement
public class Route {
    /**
     * Default route value
     */
    public static final String DEFAULT_ROUTE = "*";

    /**
     * Route value
     */
    private String value;

    /**
     * Returns the route value or {@code null} if it does not exist
     *
     * @return route value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the route value
     *
     * @param value route value
     */
    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }
}