package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;

public interface ItemConverter {

    Item fromDTO (ItemDTO itemDTO);

    ItemDTO toDTO (Item item);
}
