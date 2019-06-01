package com.gmail.salahub.nikolay.online.market.nsalahub.repository.model;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ARTICLES")
@SQLDelete(sql =
        "UPDATE ARTICLES " +
                "SET a_deleted = 1 " +
                "WHERE a_id = ?")
@Where(clause = "a_deleted = 0")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long id;
    @Column(name = "a_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "a_title")
    private String title;
    @ManyToOne(optional = false)
    @JoinColumn(name = "a_user_id")
    private User user;
    @Column(name = "a_content")
    private String content;
    @Column(name = "a_deleted")
    private boolean isDeleted;
    @OrderBy(value = "c_date_created desc")
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "article")
    List<Comment> comments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return isDeleted == article.isDeleted &&
                Objects.equals(id, article.id) &&
                Objects.equals(date, article.date) &&
                Objects.equals(title, article.title) &&
                Objects.equals(user.getId(), article.user.getId()) &&
                Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, title, user, content, isDeleted);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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
