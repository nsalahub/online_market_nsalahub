package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.parser.ItemXmlDTO;

public interface ItemXMLConverter {
    Item fromDTO (ItemXmlDTO itemXmlDTO);
}
