package com.gmail.salahub.nikolay.online.market.nsalahub.repository.model;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
@SQLDelete(sql =
        "UPDATE comments " +
                "SET c_deleted = 1 " +
                "WHERE c_id = ?")
@Where(clause = "c_deleted = 0")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_article_id")
    private Article article;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_user_id")
    private User user;
    @Column(name = "c_content")
    private String content;
    @Column(name = "c_date_created")
    private Date date;
    @Column(name = "c_deleted")
    private boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return isDeleted == comment.isDeleted &&
                Objects.equals(id, comment.id) &&
                Objects.equals(article, comment.article) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, user, content, date, isDeleted);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
