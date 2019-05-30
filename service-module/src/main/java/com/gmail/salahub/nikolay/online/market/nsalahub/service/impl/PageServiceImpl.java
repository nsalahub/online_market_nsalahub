package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import org.springframework.stereotype.Service;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_REVIEW_VALUE;

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

    @Override
    public Integer getLimitValue(Integer limit, Integer page) {
        return (page - 1) * limit;
    }
}
