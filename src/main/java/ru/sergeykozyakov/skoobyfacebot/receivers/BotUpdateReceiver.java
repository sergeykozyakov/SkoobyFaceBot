package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

public class BotUpdateReceiver extends BotReceiver {
    private static Logger LOG = LoggerFactory.getLogger(BotUpdateReceiver.class.getName());

    public BotUpdateReceiver(BotApiContext context, Message message) {
        super(context, message);
    }

    @Override
    public void execute() {
        Message message = getMessage();

        String chatId = message.getChatId().toString();
        String messageText = message.hasText() ? message.getText() : "";
        String replyMessage = "Вы написали: " + messageText;

        LOG.info("[chatId: " + chatId + "] Received message: " + messageText);

        try {
            getApiContext().sendMessage(chatId, replyMessage);
            LOG.info("[chatId: " + chatId + "] Bot answered: " + replyMessage);
        } catch (TelegramApiException | BotException e) {
            LOG.warn("[chatId: " + chatId + "]", e);
        }
    }
}
