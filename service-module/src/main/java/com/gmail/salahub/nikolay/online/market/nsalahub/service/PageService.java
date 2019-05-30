package com.gmail.salahub.nikolay.online.market.nsalahub.service;

public interface PageService {
    Integer getValueOfPages(Integer valueOfModels, Integer limit);

    Integer getLimitValue (Integer limit , Integer page);
}
