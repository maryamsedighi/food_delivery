package com.myproject.stockApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date orderDate;
    private Float amount;
    private Integer orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private WareHouse wareHouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Customer customer;

}
