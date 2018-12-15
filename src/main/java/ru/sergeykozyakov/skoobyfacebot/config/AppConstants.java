package ru.sergeykozyakov.skoobyfacebot.config;

/**
 * Main Bot application settings
 *
 * @author Sergey Kozyakov
 */
public interface AppConstants {
    /**
     * Bot sender task service availability
     */
    boolean TELEGRAM_BOT_SENDER_TASK_ENABLED = true;

    /**
     * Start delay for Bot sender task service
     */
    int TELEGRAM_BOT_SENDER_START_DELAY = 5 * 1000;

    /**
     * Timeout for Bot sender task service
     */
    int TELEGRAM_BOT_SENDER_TIMEOUT = 60 * 1000;

    /**
     * Telegram Bot name
     */
    String TELEGRAM_BOT_NAME = "SkoobyFaceBot";
}
