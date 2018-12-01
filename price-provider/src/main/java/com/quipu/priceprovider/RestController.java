package com.quipu.priceprovider;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
class RestController {

    @GetMapping("/api/hello")
    String hello() {
        return "Hello my name is " + UUID.randomUUID().toString();
    }
}
