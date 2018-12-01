package com.quipu.priceprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class PriceDetails {
    private String productName;
    private double price;
    private String currency;
    private String providerName;
}
