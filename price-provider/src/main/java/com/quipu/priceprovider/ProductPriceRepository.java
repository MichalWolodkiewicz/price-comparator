package com.quipu.priceprovider;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
class ProductPriceRepository implements ApplicationRunner {

    private ConcurrentHashMap<String, PriceDetails> prices = new ConcurrentHashMap<>(10);

    private static final String[] productNames = new String[]{"ferrari", "audi", "volvo", "ford", "toyota"};

    private static final String[] currencies = new String[]{"USD", "EUR", "PLN"};

    PriceDetails getPriceDetails(String productName) {
        if (prices.containsKey(productName)) {
            return prices.get(productName);
        }
        return null;
    }

    @Override
    public void run(ApplicationArguments args) {
        Random random = new Random();
        String providerName = UUID.randomUUID().toString();
        for (String productName : productNames) {
            double price = random.nextDouble() * 1000;
            String currency = currencies[random.nextInt(currencies.length - 1)];
            prices.put(productName, new PriceDetails(productName, price, currency, providerName));
        }
    }
}
