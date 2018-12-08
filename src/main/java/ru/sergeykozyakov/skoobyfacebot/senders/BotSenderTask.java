package ru.sergeykozyakov.skoobyfacebot.senders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

public class BotSenderTask extends SenderTask {
    private static Logger LOG = LoggerFactory.getLogger(BotSenderTask.class.getName());

    public BotSenderTask(BotApiContext context) {
        super(context);
    }

    @Override
    public void run() {
        LOG.info("Bot Sender task executed!");
    }
}
