package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.CommentRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class CommentRepositoryImpl extends GenericRepositoryImpl<Long, Comment> implements CommentRepository {

    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImpl.class);

    @Override
    public List<Comment> getByArticleId(Long id) {
        String hqlQuery = "from Comment C WHERE C.article.id =:userId";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("userId", id);
        return query.getResultList();
    }
}
