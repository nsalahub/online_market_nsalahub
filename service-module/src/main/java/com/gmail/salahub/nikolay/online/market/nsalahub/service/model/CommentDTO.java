package com.gmail.salahub.nikolay.online.market.nsalahub.service.model;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import java.util.Date;

public class CommentDTO {
    private Long id;
    private ArticleDTO articleDTO;
    private UserDTO userDTO;
    private Date date;
    private String content;
    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
