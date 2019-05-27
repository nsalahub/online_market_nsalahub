package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;

public interface RoleRepository extends GenericRepository<Long, Role> {
    Role findByName(String role);
}
