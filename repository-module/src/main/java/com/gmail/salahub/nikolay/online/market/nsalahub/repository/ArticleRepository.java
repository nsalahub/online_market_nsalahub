package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;

public interface ArticleRepository extends GenericRepository<Long, Article> {

    void deleteById(Long id);

}
