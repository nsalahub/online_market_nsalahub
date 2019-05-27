package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.CommentConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.CommentDTO;
import org.springframework.stereotype.Component;

@Component("commentConverter")
public class CommentConverterImpl implements CommentConverter {

    private final UserConverter userConverter;
    private final ArticleConverter articleConverter;

    public CommentConverterImpl(UserConverter userConverter, ArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Comment fromDTO(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setArticle(articleConverter.fromDTO(commentDTO.getArticleDTO()));
        comment.setDate(commentDTO.getDate());
        comment.setContent(commentDTO.getContent());
        comment.setUser(userConverter.fromDTO(commentDTO.getUserDTO()));
        comment.setDeleted(commentDTO.isDeleted());
        return comment;
    }

    @Override
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setArticleDTO(articleConverter.toDTO(comment.getArticle()));
        commentDTO.setUserDTO(userConverter.toDTO(comment.getUser()));
        commentDTO.setDate(commentDTO.getDate());
        commentDTO.setContent(comment.getContent());
        commentDTO.setDeleted(comment.isDeleted());
        return commentDTO;
    }
}
