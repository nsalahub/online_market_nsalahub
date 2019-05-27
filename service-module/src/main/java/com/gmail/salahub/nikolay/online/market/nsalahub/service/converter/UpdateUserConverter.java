package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.UpdateUser;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;

public interface UpdateUserConverter {

    UpdateUser fromDTO(UpdateUserDTO updateUserDTO);

    UpdateUserDTO toDTO(UpdateUser updateUser);
}
