package ru.sergeykozyakov.skoobyfacebot.parsers;

import ru.sergeykozyakov.skoobyfacebot.entities.Root;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

/**
 * Interface for the States file reading and parsing implementation
 *
 * @author Sergey Kozyakov
 */
public interface StateReader {
    /**
     * Parses the States file
     *
     * @throws BotException if parsing error occurred
     */
    void parse() throws BotException;

    /**
     * Returns the States file unserialized root element
     *
     * @return States file unserialized root element
     */
    Root getRoot();
}
