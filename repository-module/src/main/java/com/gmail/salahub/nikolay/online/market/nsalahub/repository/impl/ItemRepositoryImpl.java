package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ItemRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public void deleteById(Long id) {
        String hqlQuery = "UPDATE Item I SET I.isDeleted = true WHERE I.id =:id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
