package ru.sergeykozyakov.skoobyfacebot.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sergeykozyakov.skoobyfacebot.api.BotApiContext;
import ru.sergeykozyakov.skoobyfacebot.commands.BotCommand;
import ru.sergeykozyakov.skoobyfacebot.commands.BotCommandFactory;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.Command;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.Keyboard;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.Root;
import ru.sergeykozyakov.skoobyfacebot.entities.xml.State;
import ru.sergeykozyakov.skoobyfacebot.exceptions.BotException;
import ru.sergeykozyakov.skoobyfacebot.parsers.BotStateReader;
import ru.sergeykozyakov.skoobyfacebot.parsers.StateReader;

import java.lang.reflect.InvocationTargetException;

/**
 * Handles Update event for Bot message
 *
 * @author Sergey Kozyakov
 */
public class BotUpdateReceiver extends BotReceiver {
    /**
     * Event logger
     */
    private static Logger LOG = LoggerFactory.getLogger(BotUpdateReceiver.class.getName());

    /**
     * Sets the Telegram Bot API objects
     *
     * @param context Telegram Bot API adapter
     * @param message Telegram Bot message entity
     */
    public BotUpdateReceiver(BotApiContext context, Message message) {
        super(context, message);
    }

    /**
     * Executes main receiver actions
     */
    @Override
    public void receive() {
        Message message = getMessage();
        String chatId = message.getChatId().toString();

        String defaultText = "[not a text]";
        String messageText = message.hasText() ? message.getText() : defaultText;

        Root statesRoot;

        try {
            StateReader stateReader = BotStateReader.getInstance();
            statesRoot = stateReader.getRoot();
        } catch (BotException e) {
            LOG.error("[chatId: " + chatId + "]", e);
            return;
        }

        Command command = statesRoot.getCommandByRoute(messageText);

        if (command == null) {
            LOG.error("[chatId: " + chatId + "] " +
                    "Bot State: can not find an appropriate command handler for message: " + messageText);
            return;
        }

        String className = command.getClassName();
        Keyboard keyboard;

        if (className == null) {
            State state = command.getStateById(State.DEFAULT_STATE);

            if (state == null) {
                LOG.error("[chatId: " + chatId + "] " +
                        "Bot State: can not find an appropriate state handler for message: " + messageText);
                return;
            }

            className = state.getClassName();

            if (className == null) {
                LOG.error("[chatId: " + chatId + "] " +
                        "Bot State: can not find an appropriate state handler name for message: " + messageText);
                return;
            } else {
                keyboard = state.getKeyboardById(Keyboard.DEFAULT_KEYBOARD);
            }
        } else {
            keyboard = command.getKeyboardById(Keyboard.DEFAULT_KEYBOARD);
        }

        if (keyboard == null) {
            keyboard = new Keyboard();
        }

        try {
            BotCommandFactory botCommandFactory = BotCommandFactory.getInstance();
            BotCommand botCommand = botCommandFactory.getCommand(className, getApiContext(), message, keyboard);

            LOG.info("[chatId: " + chatId + "] " +
                    botCommand.getClass().getSimpleName() + " is parsing received message: " + messageText);

            botCommand.execute();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException |
                IllegalAccessException | NoSuchMethodException e) {
            LOG.error("[chatId: " + chatId + "]", e);
        }
    }
}
