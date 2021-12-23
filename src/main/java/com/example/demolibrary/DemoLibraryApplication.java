package com.example.demolibrary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoLibraryApplication.class, args);
    }

    @Bean
    public MyMessenger myMessenger(@Value("${messenger4j.pageAccessToken}") String pageAccessToken,
                                   @Value("${messenger4j.appSecret}") final String appSecret,
                                   @Value("${messenger4j.verifyToken}") final String verifyToken) {
        return MyMessenger.create(pageAccessToken, appSecret, verifyToken);
    }

    @Bean
    public RestTemplate getRestTemlate() {
        return new RestTemplate();
    }
}
