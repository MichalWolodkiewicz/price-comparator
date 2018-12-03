package com.quipu.pricecomparator;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.MalformedURLException;

@RestController
class RestReactiveController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;


    RestReactiveController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/api/prices/product/{productName}")
    Flux<PriceDetails> getPrices(@PathVariable("productName") String productName) {
        return Flux.fromStream(discoveryClient.getInstances("price-provider").stream())
                .map(this::toExternalForm)
                .filter(StringUtils::isNotBlank)
                .flatMap((s) -> WebClient.create(s.concat("/api/prices/").concat(productName)).get().retrieve().bodyToMono(PriceDetails.class))
                .doOnError(System.out::println);
    }

    private String toExternalForm(ServiceInstance serviceInstance) {
        String host = "";
        try {
            host = serviceInstance.getUri().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return host;
    }

}
