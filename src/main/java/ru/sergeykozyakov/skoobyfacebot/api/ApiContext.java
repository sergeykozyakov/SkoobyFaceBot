package ru.sergeykozyakov.skoobyfacebot.api;

import org.telegram.telegrambots.bots.DefaultAbsSender;

/**
 * Adapter interface for Telegram Bot API
 *
 * @author Sergey Kozyakov
 */
public interface ApiContext {
    /**
     * Returns the Telegram Bot API object
     *
     * @return Telegram Bot API object
     */
    DefaultAbsSender getApi();
}
