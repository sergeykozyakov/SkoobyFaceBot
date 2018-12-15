package ru.sergeykozyakov.skoobyfacebot.exceptions;

/**
 * Bot exception class
 *
 * @author Sergey Kozyakov
 */
public class BotException extends Exception {
    /**
     * Bot exception constructor
     */
    public BotException() {
        super();
    }

    /**
     * Bot exception constructor
     *
     * @param message error message
     */
    public BotException(String message) {
        super(message);
    }

    /**
     * Bot exception constructor
     *
     * @param message error message
     * @param cause error object
     */
    public BotException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Bot exception constructor
     *
     * @param cause error object
     */
    public BotException(Throwable cause) {
        super(cause);
    }
}
