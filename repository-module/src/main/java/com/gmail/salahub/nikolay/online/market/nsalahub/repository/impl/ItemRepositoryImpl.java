package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ItemRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public void deleteById(Long id) {
        String hqlQuery = "UPDATE Item I SET I.isDeleted = true WHERE I.id =:id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Item> findAllWhereDeletedFalse(Integer offset, Integer limit) {
        String query = "from  Item I WHERE I.isDeleted = false";
        Query q = entityManager.createQuery(query)
                .setMaxResults(limit)
                .setFirstResult(offset);
        return q.getResultList();
    }

    @Override
    public Integer getCountOfEntitiesWhereDeletedFalse() {
        String query = "SELECT COUNT(*) FROM Item I WHERE I.isDeleted = false";
        Query q = entityManager.createQuery(query);
        return ((Number) q.getSingleResult()).intValue();
    }

}
