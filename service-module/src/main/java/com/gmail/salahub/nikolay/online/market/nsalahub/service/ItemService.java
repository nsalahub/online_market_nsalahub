package com.gmail.salahub.nikolay.online.market.nsalahub.service;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getByPageNumber(Integer page);

    int getNumberOfPages();

    ItemDTO getById(Long item);

    void deleteById(Long id);

    void create(ItemDTO itemDTO);
}
