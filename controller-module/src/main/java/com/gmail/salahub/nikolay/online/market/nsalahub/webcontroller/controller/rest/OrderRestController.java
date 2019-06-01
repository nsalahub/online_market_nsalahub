package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.rest;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.OrderService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {

    private OrderService orderService;

    @Autowired
    public OrderRestController (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    public ResponseEntity<List<OrderDTO>> showArticles() {
        List<OrderDTO> orders = orderService.getByPageNumber(1);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
