package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.dto.UserDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.UserTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserTable login(LoginDto loginDto) throws FoodDeliveryException;

    List<UserTable> listSubUsers(Integer userId) throws FoodDeliveryException;

    void save(UserDto userDto) throws FoodDeliveryException;

    UserTable getUserById(Integer id);
}
