package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.gmail.salahub.nikolay.online.market.nsalahub.service",
        "com.gmail.salahub.nikolay.online.market.nsalahub.repository",
        "com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller"})
@EntityScan("com.gmail.salahub.nikolay.online.market.nsalahub.repository.model")
public class OnlineMarketApp {

    public static void main(String[] args) {
        SpringApplication.run(OnlineMarketApp.class, args);
    }

}
