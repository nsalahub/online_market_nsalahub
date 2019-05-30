package com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.gmail.salahub.nikolay.online.market.nsalahub.service.constant.ServiceConstant.DATE_TIME_FORMAT_STRING_VALUE;

public class ArticleCreateDTO {

    private Long id;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT_STRING_VALUE)
    private Date date;
    private String title;
    private String username;
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
