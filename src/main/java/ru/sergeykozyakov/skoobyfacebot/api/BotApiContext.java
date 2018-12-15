package ru.sergeykozyakov.skoobyfacebot.api;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

/**
 * Adapter class for Telegram Bot API
 *
 * @author Sergey Kozyakov
 */
public class BotApiContext implements ApiContext {
    /**
     * Telegram Bot API object
     */
    private DefaultAbsSender api;

    /**
     * Sets the Telegram Bot API object from existing class
     *
     * @param api Telegram Bot API object
     */
    public BotApiContext(DefaultAbsSender api) {
        this.api = api;
    }

    /**
     * Returns the Telegram Bot API object
     *
     * @return Telegram Bot API object
     */
    public DefaultAbsSender getApi() {
        return api;
    }

    /**
     * Sends text message to the specified chat id
     *
     * @param chatId target chat
     * @param text message
     * @throws BotException if some arguments were not specified
     * @throws TelegramApiException if message could not be delivered
     */
    public synchronized void sendMessage(String chatId, String text) throws BotException, TelegramApiException {
        if (chatId == null) {
            throw new BotException("Parameter chatId can not be null");
        }

        if (text == null) {
            throw new BotException("Parameter text can not be null");
        }

        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(text);

        api.execute(message);
    }
}
