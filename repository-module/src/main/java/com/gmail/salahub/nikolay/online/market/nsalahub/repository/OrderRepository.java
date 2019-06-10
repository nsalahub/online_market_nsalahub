package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Order;

import java.util.List;

public interface OrderRepository extends GenericRepository<Long, Order> {
    Integer getCountOfEntitiesByUserId(Long id);

    List<Order> findAllByUserId(Integer offset, Integer limit, Long userId);
}
