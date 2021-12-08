package com.myproject.fooddelivery.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantSite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String restaurantName;
    private String description;
    private String contact;
    private String address;
    private Date lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserTable user;





}
