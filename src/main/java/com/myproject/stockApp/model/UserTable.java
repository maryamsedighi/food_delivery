package com.myproject.stockApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String contact;
    private String email;
    private String userName;
    private String password;
    private Boolean isAdmin;
    private String userCode;

    @ManyToOne
    private UserTable userAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    private WareHouse wareHouse;

}
