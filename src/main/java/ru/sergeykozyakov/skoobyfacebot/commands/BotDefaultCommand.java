package ru.sergeykozyakov.skoobyfacebot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

public class BotDefaultCommand extends BotCommand {
    private static Logger LOG = LoggerFactory.getLogger(BotDefaultCommand.class.getName());

    public BotDefaultCommand(BotApiContext context, Message message) {
        super(context, message);
    }

    @Override
    public void execute() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String defaultText = "что-то нетекстовое";
        String messageText = message.hasText() ? message.getText() : defaultText;
        String replyMessage = "Вы написали: " + messageText;

        try {
            getApiContext().sendMessage(chatId, replyMessage);

            LOG.info("[chatId: " + chatId + "] " +
                    BotDefaultCommand.class.getSimpleName() + " sent message: " + replyMessage);
        } catch (TelegramApiException | BotException e) {
            LOG.warn("[chatId: " + chatId + "]", e);
        }
    }
}
