package com.quipu.pricecomparator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
class RestController {

    private final static Logger logger = LoggerFactory.getLogger(RestController.class);

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    RestController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/api/prices/product/{productName}")
    List<PriceDetails> getPrices(@PathVariable("productName") String productName) {
        return discoveryClient.getInstances("price-provider")
                .stream()
                .map(this::toExternalForm)
                .filter(StringUtils::isNotBlank)
                .map((s) -> s.concat("/api/prices/").concat(productName))
                .map((host) -> restTemplate.getForEntity(host, PriceDetails.class).getBody())
                .collect(Collectors.toList());
    }

    @GetMapping("/api/services")
    List<ServiceInstance> getServices() {
        return discoveryClient.getInstances("price-provider");
    }

    private String toExternalForm(ServiceInstance serviceInstance) {
        String host = "";
        try {
            host = serviceInstance.getUri().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        logger.info("Host = {}", host);
        return host;
    }
}
