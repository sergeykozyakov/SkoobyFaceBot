package ru.sergeykozyakov.skoobyfacebot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.entities.Keyboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Provides the appropriate Bot Command class from user text request
 *
 * @author Sergey Kozyakov
 */
public class BotCommandFactory {
    /**
     * Instance of the class
     */
    private static BotCommandFactory instance;

    /**
     * Private singleton constructor
     */
    private BotCommandFactory() {}

    /**
     * Creates the new instance of BotCommandFactory class or returns the existing one
     *
     * @return new {@code BotCommandFactory} instance
     */
    public static synchronized BotCommandFactory getInstance() {
        if (instance == null) {
            instance = new BotCommandFactory();
        }

        return instance;
    }

    /**
     * Returns the new instance of Bot Command class by params
     *
     * @param className name of the appropriate command class
     * @param api API context object
     * @param message received message
     * @param keyboard reply markup keyboard
     * @return new {@code BotCommand} instance
     * @throws ClassNotFoundException if class is not found
     * @throws NoSuchMethodException if method is not found
     * @throws IllegalAccessException if access is illegal
     * @throws InvocationTargetException if target is wrong
     * @throws InstantiationException if installation is wrong
     */
    public BotCommand getCommand(String className, BotApiContext api, Message message, Keyboard keyboard)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Class<?> cl = Class.forName(className);
        Constructor constructor = cl.getConstructor(BotApiContext.class, Message.class, Keyboard.class);

        return (BotCommand)constructor.newInstance(api, message, keyboard);
    }
}
