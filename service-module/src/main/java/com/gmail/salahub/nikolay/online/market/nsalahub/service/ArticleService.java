package com.gmail.salahub.nikolay.online.market.nsalahub.service;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;

import java.text.ParseException;
import java.util.List;

public interface ArticleService {
    int getNumberOfPages();

    List<ArticleDTO> getByPageNumber(int page);

    ArticleDTO getArticleById(Long articleId);

    void save(ArticleDTO articleDTO);

    void delete(Long id);

    void create(ArticleCreateDTO articleCreateDTO) throws ParseException;

    void update(ArticleDTO articleDTO);
}
