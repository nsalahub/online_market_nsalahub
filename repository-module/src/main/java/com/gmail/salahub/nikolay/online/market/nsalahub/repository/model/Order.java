package com.gmail.salahub.nikolay.online.market.nsalahub.repository.model;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name= "Orders")
@SQLDelete(sql =
        "UPDATE Orders " +
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "o_item_name")
    private Item item;
    @Column(name = "o_quantity")
    private Integer quantity;
    @Column(name = "o_total_price")
    private BigDecimal totalPrice;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "o_user_name")
    private User user;
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
                Objects.equals(item.getId(), order.item.getId()) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(user.getId(), order.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOrder, item, quantity, totalPrice, user, isDeleted);
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
