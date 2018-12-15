package ru.sergeykozyakov.skoobyfacebot.senders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

/**
 * Handles Sender task for working in timer mode
 *
 * @author Sergey Kozyakov
 */
public class BotSenderTask extends SenderTask {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotSenderTask.class.getName());

    /**
     * Sets the Telegram Bot API adapter
     *
     * @param context Telegram Bot API adapter
     */
    public BotSenderTask(BotApiContext context) {
        super(context);
    }

    /**
     * Bot sender task start action
     */
    @Override
    public void run() {
        LOG.info("Bot Sender task executed!");
    }
}
