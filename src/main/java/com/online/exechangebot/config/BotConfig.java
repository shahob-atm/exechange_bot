package com.online.exechangebot.config;

import java.util.ResourceBundle;

public class BotConfig {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("bot");

    public static final String BOT_TOKEN = bundle.getString("bot_token");
    public static final String BOT_USERNAME = bundle.getString("bot_username");
    public static final String CBU_API_URL = bundle.getString("CBU_API_URL");
}
