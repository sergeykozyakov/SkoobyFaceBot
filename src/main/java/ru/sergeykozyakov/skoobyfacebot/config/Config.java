package ru.sergeykozyakov.skoobyfacebot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.io.*;
import java.util.Properties;

public final class Config {
    private static Logger LOG = LoggerFactory.getLogger(Config.class.getName());

    private static Config instance;
    private Properties properties;

    private Config() {
        String fileName = "env.properties";
        File file = new File(fileName);

        if (file.exists() && !file.isDirectory()) {
            try {
                InputStream is = new FileInputStream(file.getAbsolutePath());
                properties = new Properties();
                properties.load(is);

                LOG.info("Bot Environment file was parsed successfully!");
            } catch (IOException e) {
                LOG.error("Cannot read Bot Environment file!", e);
            }
        }
    }

    private String get(String name) {
        String value;

        if (properties != null && properties.containsKey(name)) {
            value = properties.getProperty(name);
        } else {
            value = System.getenv(name);
        }

        return (value != null) ? value.trim() : null;
    }

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    public String getTelegramBotToken() {
        return get("TELEGRAM_BOT_TOKEN");
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
