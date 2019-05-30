package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.StringOperationService;
import org.springframework.stereotype.Service;

@Service("stringOperationService")
public class StringOperationServiceImpl implements StringOperationService {

    private static final Integer ARTICLE_SHORT_CONTAINED_STRING_VALUE = 200;

    @Override
    public String getSummary(String startString) {
        char[] chars = startString.toCharArray();
        String stringShortArticle = "";
        if (chars.length < ARTICLE_SHORT_CONTAINED_STRING_VALUE) {
            for (int i = 0; i < chars.length; i++) {
                stringShortArticle = stringShortArticle + chars[i];
            }
        } else {
            for (int i = 0; i < ARTICLE_SHORT_CONTAINED_STRING_VALUE; i++) {
                stringShortArticle = stringShortArticle + chars[i];
            }
        }
        return stringShortArticle;
    }
}
