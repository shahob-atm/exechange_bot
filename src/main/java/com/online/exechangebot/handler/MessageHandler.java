package com.online.exechangebot.handler;

import com.online.exechangebot.command.MessageCommand;
import com.online.exechangebot.service.BotService;
import com.online.exechangebot.service.KeyboardService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final BotService botService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MessageHandler(BotService botService) {
        this.botService = botService;
    }

    public void handleTextMessage(Update update) {
        executorService.execute(() -> processTextMessage(update));
    }

    private void processTextMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        MessageCommand command = MessageCommand.fromString(text);

        if (command == null) {
            botService.sendMessage(update.getMessage(), new SendMessage(chatId, "❓ Noma'lum buyruq,  /help  bilan yordam oling!"));
            return;
        }

        if (command.equals(MessageCommand.START)) {
            botService.sendMessage(update.getMessage(), generateStartCommandMessage(chatId));
        }

        if (command.equals(MessageCommand.HELP)) {
            botService.sendMessage(update.getMessage(), generateHelpCommandMessage(chatId));
        }
    }

    private static SendMessage generateStartCommandMessage(String chatId) {
        String text = """
        <b>💰 Valyuta kurslari botiga xush kelibsiz!</b>
        \nSiz USD yoki RUB valyutalarining so‘mga nisbatan joriy kursini bilishingiz mumkin.
        \n<i>Davom etish uchun pastdagi tugmalardan birini bosing:</i>
        """;

        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(KeyboardService.getInlineKeyboardMarkupUsdRub());
        message.setParseMode("HTML");
        return message;
    }

    private static SendMessage generateHelpCommandMessage(String chatId) {
        String text = """
        💁‍♂️ <b>Biz bilan bog‘lanish</b>
        \nAgar sizda savollar bo‘lsa yoki qo‘shimcha ma’lumot kerak bo‘lsa, quyidagi raqamga bog‘laning:
        \n📱 <b>Telefon raqam:</b> <a href="tel:+998909876754">+998 (90) 987-67-54</a>
        """;

        SendMessage message = new SendMessage(chatId, text);
        message.setParseMode("HTML");
        return message;
    }
}
