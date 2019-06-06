package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;

import java.util.List;

public interface ItemRepository extends GenericRepository<Long, Item> {
    void deleteById(Long id);

    List<Item> findAllWhereDeletedFalse(Integer limitValue, Integer limitItemValue);

    Integer getCountOfEntitiesWhereDeletedFalse();
}
