package com.gmail.salahub.nikolay.online.market.nsalahub.repository.model;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.User;
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
import java.util.Date;

@Entity
@Table(name = "Reviews")
@SQLDelete(sql =
        "UPDATE Reviews " +
                "SET r_deleted = 1 " +
                "WHERE r_id = ?")
@Where(clause = "r_deleted = 0")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long id;
    @Column(name = "r_review")
    private String review;
    @Column(name = "r_showing")
    private boolean isShowing;
    @Column(name = "r_deleted")
    private boolean isDeleted;
    @Column(name = "r_date_create")
    private Date creatingDate;
    @ManyToOne(optional = false, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "r_user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
