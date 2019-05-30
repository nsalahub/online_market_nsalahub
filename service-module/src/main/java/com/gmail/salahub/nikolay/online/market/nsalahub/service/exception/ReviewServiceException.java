package com.gmail.salahub.nikolay.online.market.nsalahub.service.exception;

import java.sql.SQLException;

public class ReviewServiceException extends RuntimeException {
    public ReviewServiceException(String message, SQLException e) {
        super(message, e);
    }
}
