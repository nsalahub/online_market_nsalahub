package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemUploadDTO;

public interface UploadItemConverter {

    Item fromDTO(ItemUploadDTO itemUploadDTO);
}
