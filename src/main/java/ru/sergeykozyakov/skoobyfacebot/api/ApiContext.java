package ru.sergeykozyakov.skoobyfacebot.api;

import org.telegram.telegrambots.bots.DefaultAbsSender;

public interface ApiContext {
    DefaultAbsSender getApi();
}
