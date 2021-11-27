package com.myproject.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderTable order;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserTable user;

    private Float amount;
    private Integer trackingNumber;
    private Date paymentDate;


}
