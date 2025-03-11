package com.online.exechangebot.command;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.stream.Stream;

public enum CallbackCommand {
    RUB("rub"),
    USD("usd");

    private final String command;
    private static final Map<String, CallbackCommand> COMMAND_MAP = Stream.of(values())
            .collect(Collectors.toMap(CallbackCommand::getCommand, Function.identity()));

    CallbackCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CallbackCommand fromString(String command) {
        return COMMAND_MAP.getOrDefault(command.toLowerCase(), null);
    }
}
