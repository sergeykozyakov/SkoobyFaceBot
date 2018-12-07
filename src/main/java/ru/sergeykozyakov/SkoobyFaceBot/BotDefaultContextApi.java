package ru.sergeykozyakov.SkoobyFaceBot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public class BotDefaultContextApi implements BotContextApi {
    private DefaultAbsSender context;

    public BotDefaultContextApi(DefaultAbsSender context) {
        this.context = context;
    }

    public DefaultAbsSender getContextApi() {
        return context;
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

        context.execute(message);
    }
}
