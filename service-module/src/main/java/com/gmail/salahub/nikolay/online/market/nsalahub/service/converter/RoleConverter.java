package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;

public interface RoleConverter {
    Role fromDTO(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}
