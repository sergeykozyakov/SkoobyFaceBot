package ru.sergeykozyakov.SkoobyFaceBot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sergeykozyakov.SkoobyFaceBot.actions.BotAction;
import ru.sergeykozyakov.SkoobyFaceBot.actions.BotCallbackListenerAction;
import ru.sergeykozyakov.SkoobyFaceBot.actions.BotListenerAction;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public class BotHandler extends TelegramLongPollingBot {
    private static Logger LOG = LoggerFactory.getLogger(BotHandler.class.getName());

    private BotConfig config;
    private BotContextApi context;

    public BotHandler() {
        super();
        initialize();
    }

    public BotHandler(DefaultBotOptions botOptions) {
        super(botOptions);
        initialize();
    }

    private void initialize() {
        config = BotConfig.getInstance();
        context = new BotDefaultContextApi(this);
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                BotAction updateListener = new BotListenerAction(context, update.getMessage());
                updateListener.execute();
            } catch (BotException e) {
                LOG.warn("Не удалось получить входящее сообщение! " + e.getMessage());
            }
        } else if (update.hasCallbackQuery()) {
            try {
                BotAction callbackUpdateListener =
                        new BotCallbackListenerAction(context, update.getCallbackQuery().getMessage());
                callbackUpdateListener.execute();
            } catch (BotException e) {
                LOG.warn("Не удалось получить входящее сообщение! " + e.getMessage());
            }
        }
    }

    public String getBotUsername() {
        return config.getTelegramName();
    }

    public String getBotToken() {
        return config.getTelegramToken();
    }
}
