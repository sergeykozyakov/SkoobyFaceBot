package ru.sergeykozyakov.skoobyfacebot.senders;

import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

import java.util.TimerTask;

public abstract class SenderTask extends TimerTask {
    private BotApiContext context;

    public SenderTask(BotApiContext context) {
        this.context = context;
    }

    public BotApiContext getApiContext() {
        return context;
    }
}
