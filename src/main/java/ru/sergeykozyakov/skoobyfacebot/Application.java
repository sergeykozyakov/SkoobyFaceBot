package ru.sergeykozyakov.skoobyfacebot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sergeykozyakov.skoobyfacebot.config.AppConstants;
import ru.sergeykozyakov.skoobyfacebot.config.Config;
import ru.sergeykozyakov.skoobyfacebot.senders.BotSenderTask;

import java.util.Timer;

/**
 * Main class for Skooby Facebook Bot.
 *
 * @author Sergey Kozyakov
 */
public final class Application {
    private static Logger LOG = LoggerFactory.getLogger(Application.class.getName());

    /**
     * Main initializing method for Skooby Facebook Bot
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LOG.info("Bot is preparing for start");

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        Config config = Config.getInstance();

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        String proxyHost = config.getTelegramProxyHost();

        if (proxyHost != null && !proxyHost.isEmpty()) {
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(config.getTelegramProxyPort());
            botOptions.setProxyType(config.getTelegramProxyType());

            LOG.info("Bot Proxy settings were applied to connection");
        }

        boolean isConnected = false;
        SkoobyFaceBot bot = new SkoobyFaceBot(botOptions);

        try {
            telegramBotsApi.registerBot(bot);
            isConnected = true;

            LOG.info(bot.getBotUsername() + " is started successfully!");
        } catch (TelegramApiException | IllegalArgumentException e) {
            LOG.error("Cannot start " + bot.getBotUsername() + "!", e);
        }

        if (isConnected && AppConstants.TELEGRAM_BOT_SENDER_TASK_ENABLED) {
            BotSenderTask senderTask = new BotSenderTask(bot.getApiContext());

            Timer timer = new Timer();
            timer.schedule(
                    senderTask, AppConstants.TELEGRAM_BOT_SENDER_START_DELAY, AppConstants.TELEGRAM_BOT_SENDER_TIMEOUT
            );

            LOG.info("Bot Sender is started successfully!");
        }
    }
}
