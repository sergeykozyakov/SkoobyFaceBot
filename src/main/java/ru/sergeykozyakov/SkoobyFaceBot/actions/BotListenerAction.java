package ru.sergeykozyakov.SkoobyFaceBot.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.SkoobyFaceBot.BotContextApi;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public class BotListenerAction extends BotDefaultAction {
    private static Logger LOG = LoggerFactory.getLogger(BotListenerAction.class.getName());

    public BotListenerAction(BotContextApi context, Message message) throws BotException {
        super(context, message);
    }

    public void execute() {
        Message message = getMessage();

        String chatId = message.getChatId().toString();
        String messageText = message.hasText() ? message.getText() : "";
        String replyMessage = "Вы написали: " + messageText;

        LOG.info("[chatId: " + chatId + "] Получено сообщение: " + messageText);

        try {
            getContextApi().sendMessage(chatId, replyMessage);
            LOG.info("[chatId: " + chatId + "] Отправлено сообщение: " + replyMessage);
        } catch (TelegramApiException | BotException e) {
            LOG.warn("[chatId: " + chatId + "] Ошибка отправки сообщений или неуказанные параметры. " + e.getMessage());
        }
    }
}
