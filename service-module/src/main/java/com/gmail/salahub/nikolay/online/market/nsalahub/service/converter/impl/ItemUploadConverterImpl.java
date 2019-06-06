package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UploadItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemUploadDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("itemUploadConverter")
public class ItemUploadConverterImpl implements UploadItemConverter {

    @Override
    public Item fromDTO(ItemUploadDTO itemUploadDTO) {
        Item item = new Item();
        item.setUniqueNumber(itemUploadDTO.getUniqueNumber());
        item.setDescription(itemUploadDTO.getDescription());
        item.setName(itemUploadDTO.getName());
        item.setPrice(new BigDecimal(itemUploadDTO.getPrice()));
        return item;
    }
}
