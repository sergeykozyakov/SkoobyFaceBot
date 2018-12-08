package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

public abstract class BotReceiver implements Receiver {
    private BotApiContext context;
    private Message message;

    public BotReceiver(BotApiContext context, Message message) {
        this.context = context;
        this.message = message;
    }

    public BotApiContext getApiContext() {
        return context;
    }

    public Message getMessage() {
        return message;
    }
}
