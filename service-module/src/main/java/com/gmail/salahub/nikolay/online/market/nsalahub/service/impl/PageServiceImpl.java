package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import org.springframework.stereotype.Service;

@Service("pageService")
public class PageServiceImpl implements PageService {

    @Override
    public Integer getValueOfPages(Integer valueOfModels, Integer limit) {
        int valueOfPages;
        if (valueOfModels % limit != 0) {
            valueOfPages = (valueOfModels / limit) + 1;
        } else valueOfPages = valueOfModels / limit;
        return valueOfPages;
    }
}
