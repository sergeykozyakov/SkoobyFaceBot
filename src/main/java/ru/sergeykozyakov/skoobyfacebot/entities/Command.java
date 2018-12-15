package ru.sergeykozyakov.skoobyfacebot.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * XML Command entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement
public class Command {
    /**
     * List of states
     */
    private List<State> states;

    /**
     * List of routes
     */
    private List<Route> routes;

    /**
     * List of keyboards
     */
    private List<Keyboard> keyboards;

    /**
     * Command id attribute
     */
    private String id;

    /**
     * Command class attribute
     */
    private String className;

    /**
     * Returns the list of states or {@code null} if it does not exist
     *
     * @return list of states
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * Sets the list of states
     *
     * @param states list of states
     */
    @XmlElementWrapper(name = "states")
    @XmlElement(name = "state")
    public void setStates(List<State> states) {
        this.states = states;
    }

    /**
     * Returns the list of routes or {@code null} if it does not exist
     *
     * @return list of routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * Sets the list of routes
     *
     * @param routes list of routes
     */
    @XmlElementWrapper(name = "routes")
    @XmlElement(name = "route")
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

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

    /**
     * Returns the needed state by its id or {@code null} if it does not exist
     *
     * @return needed state
     */
    public State getStateByName(String stateName) {
        if (getStates() == null) {
            return null;
        }

        for (State state : getStates()) {
            if (state.getId() != null && state.getId().equals(stateName)) {
                return state;
            }

            if (!stateName.equals(State.DEFAULT_STATE)) {
                return getStateByName(State.DEFAULT_STATE);
            }
        }

        return null;
    }
}
