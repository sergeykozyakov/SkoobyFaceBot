package ru.sergeykozyakov.skoobyfacebot.managers;

import org.hibernate.SessionFactory;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

/**
 * Interface for the Bot database connection implementation
 *
 * @author Sergey Kozyakov
 */
public interface DBManager {
    /**
     * Connects to the database to store the session factory instance
     *
     * @throws BotException if connection error occurred
     */
    void init() throws BotException;

    /**
     * Returns a {@code SessionFactory} instance
     *
     * @return {@code SessionFactory} instance
     */
    SessionFactory getSessionFactory();
}
