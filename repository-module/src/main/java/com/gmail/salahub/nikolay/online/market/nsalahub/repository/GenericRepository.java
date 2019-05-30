package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import java.sql.Connection;
import java.util.List;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll(int offset, int limit);

    int getCountOfEntities();
}
