package ru.sergeykozyakov.skoobyfacebot.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * XML State entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement
public class State {
    /**
     * Default state value
     */
    public static final String DEFAULT_STATE = "default";

    /**
     * List of keyboards
     */
    private List<Keyboard> keyboards;

    /**
     * State id attribute
     */
    private String id;

    /**
     * State class attribute
     */
    private String className;

    /**
     * Returns the list of keyboards or {@code null} if it does not exist
     *
     * @return list of keyboards
     */
    public List<Keyboard> getKeyboards() {
        return keyboards;
    }

    /**
     * Sets the list of keyboards
     *
     * @param keyboards list of keyboards
     */
    @XmlElementWrapper(name = "keyboards")
    @XmlElement(name = "keyboard")
    public void setKeyboards(List<Keyboard> keyboards) {
        this.keyboards = keyboards;
    }

    /**
     * Returns the id attribute
     *
     * @return id attribute or {@code null} if it does not exist
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id attribute
     *
     * @param id id attribute
     */
    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the class attribute
     *
     * @return class attribute or {@code null} if it does not exist
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class attribute
     *
     * @param className class attribute
     */
    @XmlAttribute(name = "class")
    public void setClassName(String className) {
        this.className = className;
    }
}
