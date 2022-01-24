package com.myproject.stockApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "wareHouse")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WareHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numberOfProducts;
    private String numberOfOrders;
    private String numberOfPurchases;

    @OneToMany(mappedBy = "wareHouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductTable> productList;

    @OneToMany(mappedBy = "wareHouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderTable> orderList;

    @OneToMany(mappedBy = "wareHouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseTable> purchaseList;

    @OneToMany(mappedBy = "wareHouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserTable> userList;

}
