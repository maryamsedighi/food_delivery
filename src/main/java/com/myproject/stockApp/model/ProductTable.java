package com.myproject.stockApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productType;
    private Integer productCode;
    private Integer productPrice;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private WareHouse wareHouse;

}
