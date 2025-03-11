package com.online.exechangebot.command;

import java.util.Map;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MessageCommand {
    START("/start"),
    HELP("/help");

    private final String command;
    private static final Map<String, MessageCommand> COMMAND_MAP = Stream.of(values())
            .collect(Collectors.toMap(MessageCommand::getCommand, Function.identity()));

    MessageCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static MessageCommand fromString(String command) {
        return COMMAND_MAP.getOrDefault(command.toLowerCase(), null);
    }
}
