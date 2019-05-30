package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.article;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.CommentConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("articleCommentsConverter")
public class ArticleCommentsConverter implements ArticleConverter {

    private final UserConverter userConverter;
    private final CommentConverter commentConverter;

    @Autowired
    private ArticleCommentsConverter(UserConverter userConverter,
                                     CommentConverter commentConverter) {
        this.commentConverter = commentConverter;
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
        article.setComments(articleDTO.getCommentDTOS().stream()
                .map(commentConverter::fromDTO)
                .collect(Collectors.toList()));
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
        articleDTO.setCommentDTOS(article.getComments().stream()
                .map(commentConverter::toDTO)
                .collect(Collectors.toList()));
        return articleDTO;
    }
}
