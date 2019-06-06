package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.UserRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public void updatePassword(String password, String email) {
        String hqlQuery = "UPDATE User U SET U.password =: password WHERE U.email =: email";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("password", password);
        query.setParameter("email", email);
        query.executeUpdate();
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        String hqlQuery = "UPDATE User U SET U.isDeleted = true WHERE U.id IN(:ids)";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("ids", ids);
        query.executeUpdate();
    }

    @Override
    public User findByEmail(String email) {
        String hqlQuery = "from User as U where U.email=:email";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("email", email);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> findAllWhereDeletedFalse(Integer offset, Integer limit) {
        String query = "from  User U WHERE U.isDeleted = false";
        Query q = entityManager.createQuery(query)
                .setMaxResults(limit)
                .setFirstResult(offset);
        return q.getResultList();
    }

    @Override
    public Integer getCountOfEntitiesWhereDeletedFalse() {
        String query = "SELECT COUNT(*) FROM User U WHERE U.isDeleted = false";
        Query q = entityManager.createQuery(query);
        return ((Number) q.getSingleResult()).intValue();
    }
}
