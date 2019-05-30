package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component("itemConverter")
public class ItemConverterImpl implements ItemConverter {
    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setDeleted(itemDTO.isDeleted());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setUniqueNumber(itemDTO.getUniqueNumber());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDeleted(item.isDeleted());
        itemDTO.setUniqueNumber(item.getUniqueNumber());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }
}
