package com.myproject.fooddelivery.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MenuRate> menuRates;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderTable> orderTableList;
}
