package com.delivery.fastfood.domain.entities.orders;


import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isCart = true;
    private String yourAdress;
    private Float distance;
    private String phone;
    private Double totalPrice;
    private Status status;
    private Integer amountOfProducts;
    private LocalDateTime deliveryTime;

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(Integer amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getCart() {
        return isCart;
    }

    public void setCart(Boolean cart) {
        isCart = cart;
    }

    public String getYourAdress() {
        return yourAdress;
    }

    public void setYourAdress(String yourAdress) {
        this.yourAdress = yourAdress;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}