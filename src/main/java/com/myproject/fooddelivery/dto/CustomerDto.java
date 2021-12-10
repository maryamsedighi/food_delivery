package com.myproject.fooddelivery.dto;

import com.myproject.fooddelivery.model.AccountStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
    private AccountStatus accountStatus;
    private byte[] photo;

}
