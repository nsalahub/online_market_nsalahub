package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.article;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import org.springframework.stereotype.Component;

@Component("articleSaveConverter")
public class ArticleSaveConverterImpl implements ArticleConverter {
    @Override
    public Article fromDTO(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setDate(articleDTO.getDate());
        article.setContent(articleDTO.getContent());
        article.setTitle(articleDTO.getTitle());
        return article;
    }

    @Override
    public ArticleDTO toDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setDate(article.getDate());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        return articleDTO;
    }
}
