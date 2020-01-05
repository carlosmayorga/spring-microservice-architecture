package com.cmayorga.spring.test.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
    @Bean("restTemplateClient")
    @LoadBalanced
    public RestTemplate restTemplateRegister() {
        return new RestTemplate();
    }

}
