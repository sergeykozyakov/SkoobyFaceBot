package ru.sergeykozyakov.skoobyfacebot.api;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;

public class BotApiContext implements ApiContext {
    private DefaultAbsSender api;

    public BotApiContext(DefaultAbsSender api) {
        this.api = api;
    }

    public DefaultAbsSender getApi() {
        return api;
    }

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
