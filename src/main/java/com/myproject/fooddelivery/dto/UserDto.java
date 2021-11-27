package com.myproject.fooddelivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;
    private String fullName;
    private String contact;
    private String email;
    private String userName;
    private String password;
    private Boolean isAdmin;
    private Integer userAdminId;
}
