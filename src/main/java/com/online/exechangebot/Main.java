package com.online.exechangebot;

import com.online.exechangebot.bot.ExchangeBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        ExchangeBot bot = startBot();

        // Bot to‘xtaganda avtomatik `shutdown()` chaqirilishi uchun hook qo‘shamiz
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("🔄 Bot to‘xtatilmoqda...");
            assert bot != null;
            bot.shutdown();
        }));
    }

    private static ExchangeBot startBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            ExchangeBot bot = new ExchangeBot();
            botsApi.registerBot(bot);
            System.out.println("✅ Valyuta bot ishga tushdi!");
            return bot;
        } catch (TelegramApiException e) {
            System.err.println("❌ Botni ishga tushirishda xatolik!");
            e.printStackTrace();
            return null;
        }
    }
}
