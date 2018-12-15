package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

/**
 * Handles Update event for Callback Bot message
 *
 * @author Sergey Kozyakov
 */
public class BotUpdateCallbackReceiver extends BotReceiver {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotUpdateCallbackReceiver.class.getName());

    /**
     * Sets the Telegram Bot API objects
     *
     * @param context Telegram Bot API adapter
     * @param message Telegram Bot message entity
     */
    public BotUpdateCallbackReceiver(BotApiContext context, Message message) {
        super(context, message);
    }

    /**
     * Executes main receiver actions
     */
    @Override
    public void receive() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String defaultText = "[not a text]";
        String messageText = message.hasText() ? message.getText() : defaultText;

        LOG.info("[chatId: " + chatId + "] " +
                BotUpdateCallbackReceiver.class.getSimpleName() + " received callback message: " + messageText);
    }
}
