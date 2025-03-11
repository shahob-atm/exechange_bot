package com.online.exechangebot.bot;

import com.online.exechangebot.config.BotConfig;
import com.online.exechangebot.handler.CallbackHandler;
import com.online.exechangebot.handler.MessageHandler;
import com.online.exechangebot.service.BotService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public class ExchangeBot extends TelegramLongPollingBot {
    private final CallbackHandler callbackHandler;
    private final MessageHandler messageHandler;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public ExchangeBot() {
        BotService botService = new BotService(this);
        this.callbackHandler = new CallbackHandler(botService);
        this.messageHandler = new MessageHandler(botService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Har bir foydalanuvchi so‘rovi alohida oqimda bajariladi
        executorService.execute(() -> processUpdate(update));
    }

    private void processUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageHandler.handleTextMessage(update);
        } else if (update.hasCallbackQuery()) {
            callbackHandler.handleCallbackQuery(update.getCallbackQuery());
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
