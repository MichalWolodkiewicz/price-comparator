package com.quipu.pricecomparator;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
class RestController {

    private final DiscoveryClient discoveryClient;

    RestController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    List<String> hello() {
        return discoveryClient.getServices();
    }
}
