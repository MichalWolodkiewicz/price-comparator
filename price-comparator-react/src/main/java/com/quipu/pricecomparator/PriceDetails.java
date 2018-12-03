package com.quipu.pricecomparator;

import lombok.Data;

@Data
class PriceDetails {
    private String productName;
    private double price;
    private String currency;
    private String providerName;
}
