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

/**
 * Main Sender API class for Skooby Facebook Bot.
 *
 * @author Sergey Kozyakov
 */
public class SkoobyFaceBot extends TelegramLongPollingBot {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(SkoobyFaceBot.class.getName());

    /**
     * Bot configuration
     */
    private Config config;

    /**
     * Telegram Bot API adapter
     */
    private BotApiContext context;

    /**
     * Initializes the Bot and starts the connection
     */
    public SkoobyFaceBot() {
        super();
        initialize();
    }

    /**
     * Initializes the Bot and starts the connection with proxy settings
     *
     * @param botOptions Bot connection options
     */
    public SkoobyFaceBot(DefaultBotOptions botOptions) {
        super(botOptions);
        initialize();
    }

    /**
     * Initializes the Bot configuration and Bot API adapter
     */
    private void initialize() {
        config = Config.getInstance();
        context = new BotApiContext(this);
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
     * Handles the Update message receiver action
     *
     * @param update Bot Update entity
     */
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

    /**
     * Returns the Bot name or {@code null} if value does not exist
     *
     * @return Bot name
     */
    @Override
    public String getBotUsername() {
        return AppConstants.TELEGRAM_BOT_NAME;
    }

    /**
     * Returns the Bot token or {@code null} if value does not exist
     *
     * @return Bot token
     */
    @Override
    public String getBotToken() {
        return config.getTelegramBotToken();
    }
}
