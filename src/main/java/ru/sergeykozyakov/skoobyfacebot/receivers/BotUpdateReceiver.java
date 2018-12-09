package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.commands.BotCommand;
import ru.sergeykozyakov.skoobyfacebot.commands.BotDefaultCommand;
import ru.sergeykozyakov.skoobyfacebot.commands.BotStartCommand;

public class BotUpdateReceiver extends BotReceiver {
    private static Logger LOG = LoggerFactory.getLogger(BotUpdateReceiver.class.getName());

    public BotUpdateReceiver(BotApiContext context, Message message) {
        super(context, message);
    }

    @Override
    public void receive() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String defaultText = "[not a text]";
        String messageText = message.hasText() ? message.getText() : defaultText;

        BotCommand command;

        switch (messageText) {
            case "/start":
                command = new BotStartCommand(getApiContext(), message);
                break;
            default:
                command = new BotDefaultCommand(getApiContext(), message);
                break;
        }

        LOG.info("[chatId: " + chatId + "] " +
                command.getClass().getSimpleName() + " is parsing received message: " + messageText);

        command.execute();
    }
}
