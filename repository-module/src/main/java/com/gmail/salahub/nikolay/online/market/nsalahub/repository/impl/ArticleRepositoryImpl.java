package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ArticleRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("articleRepository")
public class ArticleRepositoryImpl extends GenericRepositoryImpl<Long, Article> implements ArticleRepository {

    @Override
    public void deleteById(Long id) {
        String hqlQuery = "UPDATE Article A SET A.isDeleted = true WHERE A.id =:id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
