package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/private")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public String showOrders(){

        logger.info("stat showing orders");
        return "orders";
    }
}
