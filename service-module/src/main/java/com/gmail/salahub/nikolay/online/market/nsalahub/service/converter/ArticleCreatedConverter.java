package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;

public interface ArticleCreatedConverter {

    Article fromDTO (ArticleCreateDTO articleCreateDTO);
}
