package ru.sergeykozyakov.skoobyfacebot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

public class BotStartCommand extends BotCommand {
    private static Logger LOG = LoggerFactory.getLogger(BotStartCommand.class.getName());

    public BotStartCommand(BotApiContext context, Message message) {
        super(context, message);
    }

    @Override
    public void execute() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String replyMessage = "Привет! Добро пожаловать в чат Skooby Facebook Bot.";

        try {
            getApiContext().sendMessage(chatId, replyMessage);

            LOG.info("[chatId: " + chatId + "] " +
                    BotStartCommand.class.getSimpleName() + " sent message: " + replyMessage);
        } catch (TelegramApiException | BotException e) {
            LOG.warn("[chatId: " + chatId + "]", e);
        }
    }
}
