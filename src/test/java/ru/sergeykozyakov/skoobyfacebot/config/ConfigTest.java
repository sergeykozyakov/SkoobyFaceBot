package ru.sergeykozyakov.skoobyfacebot.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigTest {
    private Config config;

    @Before
    public void setUp() {
        config = Config.getInstance();
    }

    @Test
    public void getTelegramBotToken() {
        String result = config.getTelegramBotToken();
        Assert.assertNotNull(result);
    }
}