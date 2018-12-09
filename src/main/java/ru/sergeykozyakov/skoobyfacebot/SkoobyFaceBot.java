package ru.sergeykozyakov.skoobyfacebot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.config.AppConstants;
import ru.sergeykozyakov.skoobyfacebot.receivers.BotReceiver;
import ru.sergeykozyakov.skoobyfacebot.receivers.BotUpdateCallbackReceiver;
import ru.sergeykozyakov.skoobyfacebot.receivers.BotUpdateReceiver;
import ru.sergeykozyakov.skoobyfacebot.config.Config;

public class SkoobyFaceBot extends TelegramLongPollingBot {
    private static Logger LOG = LoggerFactory.getLogger(SkoobyFaceBot.class.getName());

    private Config config;
    private BotApiContext context;

    public SkoobyFaceBot() {
        super();
        initialize();
    }

    public SkoobyFaceBot(DefaultBotOptions botOptions) {
        super(botOptions);
        initialize();
    }

    private void initialize() {
        config = Config.getInstance();
        context = new BotApiContext(this);
    }

    public BotApiContext getApiContext() {
        return context;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = null;
        BotReceiver receiver = null;

        if (update.hasMessage()) {
            message = update.getMessage();
            receiver = new BotUpdateReceiver(context, message);
        } else if (update.hasCallbackQuery() && (message = update.getCallbackQuery().getMessage()) != null) {
            receiver = new BotUpdateCallbackReceiver(context, message);
        }

        if (receiver != null) {
            String chatId = message.getChatId().toString();
            LOG.info("[chatId: " + chatId + "] " + receiver.getClass().getSimpleName() + " has got a message");

            receiver.receive();
        }
    }

    @Override
    public String getBotUsername() {
        return AppConstants.TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return config.getTelegramBotToken();
    }
}
