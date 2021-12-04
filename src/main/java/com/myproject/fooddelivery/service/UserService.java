package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.dto.UserDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.UserTable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserService {

    Long countSubUserOfUser(UserTable userId);

    UserTable login(LoginDto loginDto) throws FoodDeliveryException;

    List<UserTable> listSubUsers(Integer userId) throws FoodDeliveryException;

    void save(UserDto userDto) throws FoodDeliveryException;

    UserTable getUserById(Integer id) throws FoodDeliveryException;

    UserDto getUserDtoById(Integer id) throws FoodDeliveryException;

    void delete(Integer id) throws FoodDeliveryException;

    HashMap<Integer, String> findAllAdmins();
}
