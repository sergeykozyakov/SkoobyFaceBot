package ru.sergeykozyakov.skoobyfacebot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.Keyboard;

/**
 * Abstract Command class
 *
 * @author Sergey Kozyakov
 */
public abstract class BotCommand implements Command {
    /**
     * Telegram Bot API adapter
     */
    private BotApiContext context;

    /**
     * Telegram Bot message entity
     */
    private Message message;

    /**
     * Reply markup keyboard
     */
    private Keyboard keyboard;

    /**
     * Sets the Telegram Bot API objects
     *
     * @param context Telegram Bot API adapter
     * @param message Telegram Bot message entity
     * @param keyboard reply markup keyboard
     */
    public BotCommand(BotApiContext context, Message message, Keyboard keyboard) {
        this.context = context;
        this.message = message;
        this.keyboard = keyboard;
    }

    /**
     * Returns the Telegram Bot API adapter
     *
     * @return Telegram Bot API adapter
     */
    public BotApiContext getApiContext() {
        return context;
    }

    /**
     * Returns the Telegram Bot message entity
     *
     * @return Telegram Bot message entity
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Returns the reply markup keyboard
     *
     * @return reply markup keyboard
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }
}
