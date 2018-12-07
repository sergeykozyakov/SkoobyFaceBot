package ru.sergeykozyakov.SkoobyFaceBot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.SkoobyFaceBot.exceptions.BotException;

public interface BotContextApi {
    DefaultAbsSender getContextApi();

    void sendMessage(String chatId, String text) throws BotException, TelegramApiException;
}
