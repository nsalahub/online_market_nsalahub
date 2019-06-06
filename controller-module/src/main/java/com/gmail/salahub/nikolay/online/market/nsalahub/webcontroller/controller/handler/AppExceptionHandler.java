package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHandle(Model model, Exception e) {
        model.addAttribute("exception", e.getMessage());
        return "/error/500";
    }
}
