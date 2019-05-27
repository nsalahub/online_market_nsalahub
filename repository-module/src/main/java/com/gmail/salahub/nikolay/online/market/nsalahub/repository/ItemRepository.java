package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;

public interface ItemRepository extends GenericRepository<Long, Item> {
    void deleteById(Long id);
}
