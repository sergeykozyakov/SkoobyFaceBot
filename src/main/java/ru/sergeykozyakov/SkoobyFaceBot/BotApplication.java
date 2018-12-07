package ru.sergeykozyakov.SkoobyFaceBot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotApplication {
    private static Logger LOG = LoggerFactory.getLogger(BotApplication.class.getName());

    public static void main(String[] args) {
        LOG.info("Инициализация бота началась");
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        BotConfig config = BotConfig.getInstance();

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        String proxyHost = config.getTelegramProxyHost();

        if (proxyHost != null && !proxyHost.isEmpty()) {
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(config.getTelegramProxyPort());
            botOptions.setProxyType(config.getTelegramProxyType());

            LOG.info("Установлены настройки прокси-сервера");
        }

        BotHandler botHandler = new BotHandler(botOptions);

        try {
            telegramBotsApi.registerBot(botHandler);
            LOG.info("Бот " + botHandler.getBotUsername() + " успешно запущен");
        } catch (TelegramApiException | IllegalArgumentException e) {
            LOG.error("Не удалось запустить бот! Недоступен сервер API или указан неверный токен доступа. " + e.getMessage());
        }
    }
}
