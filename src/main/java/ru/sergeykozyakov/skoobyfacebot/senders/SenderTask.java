package ru.sergeykozyakov.skoobyfacebot.senders;

import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

import java.util.TimerTask;

/**
 * Abstract Sender task service class
 *
 * @author Sergey Kozyakov
 */
public abstract class SenderTask extends TimerTask {
    /**
     * Telegram Bot API adapter
     */
    private BotApiContext context;

    /**
     * Sets the Telegram Bot API adapter
     *
     * @param context Telegram Bot API adapter
     */
    public SenderTask(BotApiContext context) {
        this.context = context;
    }

    /**
     * Returns the Telegram Bot API adapter
     *
     * @return Telegram Bot API adapter
     */
    public BotApiContext getApiContext() {
        return context;
    }
}
