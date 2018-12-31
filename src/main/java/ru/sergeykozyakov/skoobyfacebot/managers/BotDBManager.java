package ru.sergeykozyakov.skoobyfacebot.managers;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

/**
 * DB helper class for connecting to the Bot Database
 *
 * @author Sergey Kozyakov
 */
public class BotDBManager implements DBManager {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotDBManager.class.getName());

    /**
     * Instance of the class
     */
    private static BotDBManager instance;

    /**
     * Instance of the Bot Database session factory
     */
    private SessionFactory sessionFactory;

    /**
     * Private singleton constructor
     *
     * @throws BotException if connection error occurred
     */
    private BotDBManager() throws BotException {
        init();
    }

    /**
     * Creates the new instance of BotDBManager class or returns the existing one
     *
     * @return new {@code BotDBManager} instance
     * @throws BotException if connection error occurred
     */
    public static synchronized BotDBManager getInstance() throws BotException {
        if (instance == null) {
            instance = new BotDBManager();
        }

        return instance;
    }

    /**
     * Connects to the database to store the session factory instance
     *
     * @throws BotException if connection error occurred
     */
    @Override
    public void init() throws BotException {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();

            LOG.info("Bot DB Manager has been connected to the database successfully!");
        } catch (HibernateException e) {
            throw new BotException(e);
        }
    }

    /**
     * Returns a {@code SessionFactory} instance
     *
     * @return {@code SessionFactory} instance
     */
    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
