package com.myproject.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "menu")
public class FoodMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String menuName;
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuType menuType;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] menuImage;

    private String ingredients;
    private Integer menuStatus;

    @OneToMany(mappedBy = "foodMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MenuRate> menuRateList;

    @OneToMany(mappedBy = "foodMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


}
