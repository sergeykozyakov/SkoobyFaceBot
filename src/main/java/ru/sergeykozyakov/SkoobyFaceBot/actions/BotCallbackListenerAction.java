package ru.sergeykozyakov.SkoobyFaceBot.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.SkoobyFaceBot.BotContextApi;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public class BotCallbackListenerAction extends BotDefaultAction {
    private static Logger LOG = LoggerFactory.getLogger(BotCallbackListenerAction.class.getName());

    public BotCallbackListenerAction(BotContextApi context, Message message) throws BotException {
        super(context, message);
    }

    public void execute() {
        Message message = getMessage();

        String chatId = message.getChatId().toString();
        String callbackMessageText = message.hasText() ? message.getText() : "";

        LOG.info("[chatId: " + chatId + "] Получено callback-сообщение: " + callbackMessageText);
    }
}
