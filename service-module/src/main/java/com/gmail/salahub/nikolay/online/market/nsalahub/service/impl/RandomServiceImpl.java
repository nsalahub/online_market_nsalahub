package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.RandomService;
import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {

    private static final String SYMBOLS_FOR_RANDOM_STRING =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    @Override
    public String getRandomString(int beginningOfLine, int endOfLine) {
        StringBuilder randString = new StringBuilder();
        int count = (int) (Math.random() * beginningOfLine) + endOfLine;
        for (int i = 0; i < count; i++)
            randString.append(SYMBOLS_FOR_RANDOM_STRING
                    .charAt((int) (Math.random() * SYMBOLS_FOR_RANDOM_STRING.length())));

        return randString.toString();
    }
}
