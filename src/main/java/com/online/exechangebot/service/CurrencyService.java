package com.online.exechangebot.service;

import com.online.exechangebot.config.BotConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class CurrencyService {

    public static CompletableFuture<String> getCurrencyRateAsync(String currency) {
        return CompletableFuture.supplyAsync(() -> getCurrencyRate(currency));
    }

    public static String getCurrencyRate(String currency) {
        String apiUrl = BotConfig.CBU_API_URL + currency + "/latest/";

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String rate = jsonObject.getString("Rate");

            return "<b>1 " + currency.toUpperCase() + " = " + rate + " UZS</b>";
        } catch (IOException e) {
            return "❌ Xatolik! Kursni olishning iloji bo‘lmadi.";
        }
    }
}

