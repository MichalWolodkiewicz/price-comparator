package com.quipu.priceprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
class RestController {

    private final ProductPriceRepository productPriceRepository;

    RestController(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @GetMapping("/api/prices/{productName}")
    PriceDetails getPriceDetails(@PathVariable("productName") String productName) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productPriceRepository.getPriceDetails(productName);
    }
}
