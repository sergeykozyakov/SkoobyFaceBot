package ru.sergeykozyakov.skoobyfacebot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.io.*;
import java.util.Properties;

/**
 * Collects special information like tokens, API keys and proxies
 * from OS environment variables or (debug mode) from {@code env.properties} file.
 *
 * @author Sergey Kozyakov
 */
public final class Config {
    private static Logger LOG = LoggerFactory.getLogger(Config.class.getName());

    /**
     * Instance of the class
     */
    private static Config instance;

    /**
     * Java Properties file handler object
     */
    private Properties properties;

    /**
     * Reads and parses the {@code env.properties} file if it exists
     */
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

    /**
     * Returns the value of requested param from {@code env.properties} file
     * or from OS environment variables. Returns {@code null} if value does not exist.
     *
     * @param name requested param name
     * @return param value
     */
    private String get(String name) {
        String value;

        if (properties != null && properties.containsKey(name)) {
            value = properties.getProperty(name);
        } else {
            value = System.getenv(name);
        }

        return (value != null) ? value.trim() : null;
    }

    /**
     * Creates the new instance of Config class or returns the existing one
     *
     * @return new {@code Config} instance
     */
    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    /**
     * Returns the value of Telegram bot token from {@code env.properties} file
     * or from OS environment variables. Returns {@code null} if value does not exist.
     *
     * @see #get(String)
     * @return Telegram bot token
     */
    public String getTelegramBotToken() {
        return get("TELEGRAM_BOT_TOKEN");
    }

    /**
     * Returns the value of Telegram proxy host from {@code env.properties} file
     * or from OS environment variables. Returns {@code null} if value does not exist.
     *
     * @see #get(String)
     * @return Telegram proxy host
     */
    public String getTelegramProxyHost() {
        return get("TELEGRAM_PROXY_HOST");
    }

    /**
     * Returns the value of Telegram proxy port from {@code env.properties} file
     * or from OS environment variables. Returns {@code null} if value does not exist.
     *
     * @see #get(String)
     * @return Telegram proxy port
     */
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

    /**
     * Returns the value of Telegram proxy type from {@code env.properties} file
     * or from OS environment variables. Returns {@code null} if value does not exist.
     *
     * @see #get(String)
     * @return Telegram proxy type
     */
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
