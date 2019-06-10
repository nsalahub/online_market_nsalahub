package com.gmail.salahub.nikolay.online.market.nsalahub.repository.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "`Order`")
@SQLDelete(sql =
        "UPDATE `Order` " +
                "SET o_deleted = 1 " +
                "WHERE o_id = ?")
@Where(clause = "o_deleted = 0")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    private Long id;
    @Column(name = "o_number_order")
    private String numberOrder;
    @Column(name = "o_status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(optional = false, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "o_item_id")
    private Item item;
    @Column(name = "o_quantity")
    private Integer quantity;
    @Column(name = "o_total_price")
    private BigDecimal totalPrice;
    @ManyToOne(optional = false, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "o_user_id")
    private User user;
    @Column(name = "o_date_create")
    private Date dateCreated;
    @Column(name = "o_deleted")
    private boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isDeleted == order.isDeleted &&
                Objects.equals(id, order.id) &&
                Objects.equals(numberOrder, order.numberOrder) &&
                status == order.status &&
                Objects.equals(item.getId(), order.item.getId()) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(user.getId(), order.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOrder, status, item, quantity, totalPrice, user, isDeleted);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
