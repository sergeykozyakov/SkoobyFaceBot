package ru.sergeykozyakov.skoobyfacebot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.entities.Keyboard;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

/**
 * Handles Start Bot Command
 *
 * @author Sergey Kozyakov
 */
public class BotStartCommand extends BotCommand {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotStartCommand.class.getName());

    /**
     * Sets the Telegram Bot API objects
     *
     * @param context Telegram Bot API adapter
     * @param message Telegram Bot message entity
     * @param keyboard reply markup keyboard
     */
    public BotStartCommand(BotApiContext context, Message message, Keyboard keyboard) {
        super(context, message, keyboard);
    }

    /**
     * Executes main command actions
     */
    @Override
    public void execute() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String replyMessage = "Привет! Добро пожаловать в чат Skooby Facebook Bot.";

        try {
            getApiContext().sendMessage(chatId, replyMessage, getKeyboard());

            LOG.info("[chatId: " + chatId + "] " +
                    BotStartCommand.class.getSimpleName() + " sent message: " + replyMessage);
        } catch (TelegramApiException | BotException e) {
            LOG.error("[chatId: " + chatId + "]", e);
        }
    }
}
