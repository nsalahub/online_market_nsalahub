package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.article;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleCreatedConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import org.springframework.stereotype.Component;

@Component("articleCreatedConverter")
public class ArticleCreatedConverterImpl implements ArticleCreatedConverter {
    @Override
    public Article fromDTO(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article();
        article.setDate(articleCreateDTO.getDate());
        article.setTitle(articleCreateDTO.getTitle());
        article.setContent(articleCreateDTO.getContent());
        return article;
    }
}
