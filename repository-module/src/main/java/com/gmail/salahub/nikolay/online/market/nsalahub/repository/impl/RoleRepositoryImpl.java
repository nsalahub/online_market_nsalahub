package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.RoleRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Long, Role> implements RoleRepository {
    @Override
    public Role findByName(String role) {
        String hqlQuery = "from Role as R where R.name=:role";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }
}
