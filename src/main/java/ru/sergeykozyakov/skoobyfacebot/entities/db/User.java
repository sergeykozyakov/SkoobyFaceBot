package ru.sergeykozyakov.skoobyfacebot.entities.db;

import javax.persistence.*;

/**
 * Database Bot user entity
 *
 * @author Sergey Kozyakov
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * User id
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * User Telegram chat_id
     */
    @Column(name = "chat_id")
    private String chatId;

    /**
     * User Telegram Bot state
     */
    private String state;

    /**
     * Returns user id or {@code null} if it does not exist
     *
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets user id
     *
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns user Telegram chat_id or {@code null} if it does not exist
     *
     * @return user Telegram chat_id
     */
    public String getChatId() {
        return chatId;
    }

    /**
     * Sets user Telegram chat_id
     *
     * @param chatId user Telegram chat_id
     */
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    /**
     * Returns user Telegram Bot state or {@code null} if it does not exist
     *
     * @return user Telegram Bot state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets user Telegram Bot state
     *
     * @param state user Telegram Bot state
     */
    public void setState(String state) {
        this.state = state;
    }
}
