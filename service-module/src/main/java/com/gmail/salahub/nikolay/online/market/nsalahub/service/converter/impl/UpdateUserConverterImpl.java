package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.UpdateUser;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UpdateUserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import org.springframework.stereotype.Component;

@Component("updateUserConverter")
public class UpdateUserConverterImpl implements UpdateUserConverter {

    @Override
    public UpdateUser fromDTO(UpdateUserDTO updateUserDTO) {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setEmail(updateUserDTO.getEmail());
        updateUser.setRole(updateUserDTO.getRole());
        return updateUser;
    }

    @Override
    public UpdateUserDTO toDTO(UpdateUser updateUser) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setEmail(updateUser.getEmail());
        updateUserDTO.setRole(updateUser.getRole());
        return updateUserDTO;
    }
}
