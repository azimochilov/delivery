package com.delivery.fastfood.domain.entities;

import com.delivery.fastfood.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Menu> products;
    private Date createdAt;
    private String yourAdress;
    private Float distance;
    private Status status;
}
