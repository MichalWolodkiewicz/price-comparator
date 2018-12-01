package com.quipu.priceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PriceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceProviderApplication.class, args);
    }
}
