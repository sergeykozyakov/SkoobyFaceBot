package ru.sergeykozyakov.skoobyfacebot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

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
     * Sets the Telegram Bot API objects
     *
     * @param context Telegram Bot API adapter
     * @param message Telegram Bot message entity
     */
    public BotCommand(BotApiContext context, Message message) {
        this.context = context;
        this.message = message;
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
}
