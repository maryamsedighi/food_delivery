package com.myproject.fooddelivery.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String fullName;
    private String contact;
    private String email;
    private String userName;
    private String password;
    private Boolean isAdmin;
    private Integer userAdminId;
    private String userCode;
}
