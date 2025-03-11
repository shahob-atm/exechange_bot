package com.online.exechangebot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class KeyboardService {
    public static InlineKeyboardMarkup getInlineKeyboardMarkupUsdRub() {
        InlineKeyboardButton usdButton = new InlineKeyboardButton("💵 USD -> UZS");
        usdButton.setCallbackData("usd");

        InlineKeyboardButton rubButton = new InlineKeyboardButton("🇷🇺 RUB -> UZS");
        rubButton.setCallbackData("rub");

        List<InlineKeyboardButton> row = List.of(usdButton, rubButton);

        return new InlineKeyboardMarkup(List.of(row));
    }
}
