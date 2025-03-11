package com.online.exechangebot.handler;

import com.online.exechangebot.command.CallbackCommand;
import com.online.exechangebot.service.BotService;
import com.online.exechangebot.service.CurrencyService;
import com.online.exechangebot.service.KeyboardService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class CallbackHandler {
    private final BotService botService;

    public CallbackHandler(BotService botService) {
        this.botService = botService;
    }

    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        String chatId = callbackQuery.getMessage().getChatId().toString();

        CallbackCommand command = CallbackCommand.fromString(data);

        if (command != null) {
            CurrencyService.getCurrencyRateAsync(data).thenAccept(currencyRate -> {
                SendMessage message = new SendMessage(chatId, currencyRate);
                message.setReplyMarkup(KeyboardService.getInlineKeyboardMarkupUsdRub());
                message.setParseMode("HTML");
                botService.sendMessage(callbackQuery.getMessage(), message);
            });
        } else {
            botService.sendMessage(callbackQuery.getMessage(), new SendMessage(chatId, "🔴 Xatolik yuz berdi! \n✏️ Iltimos qayta urinib ko'ring!"));
        }
    }
}
