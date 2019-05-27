package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.article;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("articleConverter")
public class ArticleConverterImpl implements ArticleConverter {

    private final UserConverter userConverter;

    @Autowired
    private ArticleConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Article fromDTO(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setDate(articleDTO.getDate());
        article.setContent(articleDTO.getContent());
        article.setUser(userConverter.fromDTO(articleDTO.getUserDTO()));
        article.setTitle(articleDTO.getTitle());
        return article;
    }

    @Override
    public ArticleDTO toDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setDate(article.getDate());
        articleDTO.setContent(article.getContent());
        articleDTO.setUserDTO(userConverter.toDTO(article.getUser()));
        return articleDTO;
    }
}
