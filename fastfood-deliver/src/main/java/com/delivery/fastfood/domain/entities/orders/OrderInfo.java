package com.delivery.fastfood.domain.entities.orders;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "orderInfo")
public class OrderInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String yourAdress;
    private Float distance;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
