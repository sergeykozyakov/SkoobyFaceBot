package ru.sergeykozyakov.skoobyfacebot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

public abstract class BotCommand implements Command {
    private BotApiContext context;
    private Message message;

    public BotCommand(BotApiContext context, Message message) {
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
