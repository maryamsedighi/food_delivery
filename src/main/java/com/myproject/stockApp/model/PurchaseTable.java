package com.myproject.stockApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "purchase")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date purchaseDate;
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private WareHouse wareHouse;
}
