package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemXMLConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.parser.ItemXmlDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("itemXMLConverter")
public class ItemXMLConverterImpl implements ItemXMLConverter {

    @Override
    public Item fromDTO(ItemXmlDTO itemXmlDTO) {
        Item item = new Item();
        item.setName(itemXmlDTO.getName());
        item.setPrice(new BigDecimal(itemXmlDTO.getPrice()));
        item.setUniqueNumber(itemXmlDTO.getUniqueNumber());
        item.setDescription(itemXmlDTO.getDescription());
        return item;
    }
}
