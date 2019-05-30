package com.gmail.salahub.nikolay.online.market.nsalahub.repository.exception;

import java.sql.SQLException;

public class ReviewRepositoryException extends RuntimeException {
    public ReviewRepositoryException(String message, SQLException e) {
        super(message, e);
    }
}
