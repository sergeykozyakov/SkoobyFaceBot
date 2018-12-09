package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;

public class BotUpdateCallbackReceiver extends BotReceiver {
    private static Logger LOG = LoggerFactory.getLogger(BotUpdateCallbackReceiver.class.getName());

    public BotUpdateCallbackReceiver(BotApiContext context, Message message) {
        super(context, message);
    }

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
