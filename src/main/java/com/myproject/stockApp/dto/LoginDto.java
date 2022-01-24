package com.myproject.stockApp.dto;

import lombok.*;

@Getter
@Setter
public class LoginDto {
    private String userName;
    private String password;
    private String ip;
}
