package com.myproject.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderTable order;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodMenu foodMenu;

    private Integer orderNumber;
    private Float totalAmount;


}
