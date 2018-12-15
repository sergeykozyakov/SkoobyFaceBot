package ru.sergeykozyakov.skoobyfacebot.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * XML Commands root container entity
 *
 * @author Sergey Kozyakov
 */
@XmlRootElement(name = "commands")
public class Root {
    /**
     * List of commands
     */
    private List<Command> commands;

    /**
     * Returns the list of commands or {@code null} if it does not exist
     *
     * @return list of commands
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Sets the list of commands
     *
     * @param commands list of commands
     */
    @XmlElement(name = "command")
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Returns the needed command by its route or {@code null} if it does not exist
     *
     * @return needed command
     */
    public Command getCommandByRoute(String routeName) {
        if (getCommands() == null) {
            return null;
        }

        for (Command command : getCommands()) {
            if (command.getStates() != null && command.getStates().size() > 0) {
                command.setClassName(null);
            }

            if (command.getRoutes() != null) {
                for (Route route : command.getRoutes()) {
                    if (route.getValue() != null && route.getValue().equals(routeName)) {
                        return command;
                    }
                }

                if (!routeName.equals(Route.DEFAULT_ROUTE)) {
                    return getCommandByRoute(Route.DEFAULT_ROUTE);
                }
            }
        }

        return null;
    }
}
