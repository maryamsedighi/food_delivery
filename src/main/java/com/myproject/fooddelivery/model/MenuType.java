package com.myproject.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class MenuType implements Serializable {
    @Id
    private Integer id;
    private String typeName;
    private String description;

    @OneToMany(mappedBy = "menuType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FoodMenu> foodMenu;

}
