package ru.sergeykozyakov.SkoobyFaceBot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.io.*;
import java.util.Properties;

public class BotConfig {
    private static Logger LOG = LoggerFactory.getLogger(BotConfig.class.getName());

    private static BotConfig instance;
    private Properties properties;

    private BotConfig() {
        String fileName = "env.properties";
        File file = new File(fileName);

        if (file.exists() && !file.isDirectory()) {
            try {
                InputStream is = new FileInputStream(file.getAbsolutePath());

                properties = new Properties();
                properties.load(is);
            } catch (IOException e) {
                LOG.warn("Cannot read env properties file! " + e.getMessage());
            }
        }
    }

    protected String get(String name) {
        String value;

        if (properties != null && properties.containsKey(name)) {
            value = properties.getProperty(name);
        } else {
            value = System.getenv(name);
        }

        return (value != null) ? value.trim() : null;
    }

    public static synchronized BotConfig getInstance() {
        if (instance == null) {
            instance = new BotConfig();
        }

        return instance;
    }

    public String getTelegramName() {
        return get("TELEGRAM_NAME");
    }

    public String getTelegramToken() {
        return get("TELEGRAM_TOKEN");
    }

    public String getTelegramProxyHost() {
        return get("TELEGRAM_PROXY_HOST");
    }

    public int getTelegramProxyPort() {
        String param = get("TELEGRAM_PROXY_PORT");
        int value = 0;

        if (param != null && !param.isEmpty()) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignored) {}
        }

        return value;
    }

    public DefaultBotOptions.ProxyType getTelegramProxyType() {
        String param = get("TELEGRAM_PROXY_TYPE");
        DefaultBotOptions.ProxyType value = DefaultBotOptions.ProxyType.NO_PROXY;

        if (param != null) {
            switch (param.toUpperCase()) {
                case "HTTP":
                    value = DefaultBotOptions.ProxyType.HTTP;
                    break;
                case "SOCKS4":
                    value = DefaultBotOptions.ProxyType.SOCKS4;
                    break;
                case "SOCKS5":
                    value = DefaultBotOptions.ProxyType.SOCKS5;
                    break;
            }
        }

        return value;
    }
}
