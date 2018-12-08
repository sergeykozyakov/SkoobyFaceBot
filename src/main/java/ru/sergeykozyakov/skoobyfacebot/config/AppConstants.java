package ru.sergeykozyakov.skoobyfacebot.config;

public interface AppConstants {
    boolean TELEGRAM_BOT_SENDER_TASK_ENABLED = true;

    int TELEGRAM_BOT_SENDER_START_DELAY = 5 * 1000;
    int TELEGRAM_BOT_SENDER_TIMEOUT = 60 * 1000;

    String TELEGRAM_BOT_NAME = "SkoobyFaceBot";
}
