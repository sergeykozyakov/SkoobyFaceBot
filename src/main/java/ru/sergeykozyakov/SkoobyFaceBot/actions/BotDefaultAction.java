package ru.sergeykozyakov.SkoobyFaceBot.actions;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.SkoobyFaceBot.BotContextApi;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public abstract class BotDefaultAction implements BotAction {
    private BotContextApi context;
    private Message message;

    public BotDefaultAction(BotContextApi context, Message message) throws BotException {
        if (context == null) {
            throw new BotException("Parameter api can not be null");
        }

        if (message == null) {
            throw new BotException("Parameter message can not be null");
        }

        this.context = context;
        this.message = message;
    }

    public BotContextApi getContextApi() {
        return context;
    }

    public Message getMessage() {
        return message;
    }
}
