package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;

public interface ArticleConverter {
    Article fromDTO(ArticleDTO articleDTO);

    ArticleDTO toDTO(Article article);
}
