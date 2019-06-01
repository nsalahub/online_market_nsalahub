package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.OrderRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("orderRepository")
public class OrderRepositoryImpl extends GenericRepositoryImpl<Long, Order> implements OrderRepository {

    @Override
    public Integer getCountOfEntitiesByUserId(Long id) {
        String query = "SELECT COUNT(*) FROM Order O  WHERE O.user.id=:userId" ;
        Query q = entityManager.createQuery(query);
        q.setParameter("userId", id);
        return ((Number) q.getSingleResult()).intValue();
    }

    @Override
    public List<Order> findAllByUserId(Integer offset, Integer limit, Long userId) {
        String query = "from  Order O WHERE O.user.id=:userId";
        Query q = entityManager.createQuery(query)
                .setMaxResults(limit)
                .setFirstResult(offset);
        q.setParameter("userId", userId);
        return q.getResultList();
    }
}
