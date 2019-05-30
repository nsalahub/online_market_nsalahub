package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;

import java.util.List;

public interface UserRepository extends GenericRepository<Long, User> {

    void updatePassword(String password, String email);

    void deleteByIds(List<Long> ids);

    User findByEmail(String email);
}
